package com.example.fqw.services;

import com.example.fqw.dto.RecordDto;
import com.example.fqw.email.EmailMessage;
import com.example.fqw.email.EmailSender;
import com.example.fqw.mapper.RecordMapper;
import com.example.fqw.repositories.RecordRepository;
import com.example.fqw.telegram.TelegramBot;
import com.example.fqw.enums.BotMessageEnum;
import com.example.fqw.utils.DateTimeUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class NotificationService {

    RecordRepository recordRepository;
    RecordMapper recordMapper;
    TelegramBot telegramBot;
    EmailSender emailSender;

    //cron = "seconds minutes hours dayOfMonth(number) month dayOfWeek"
    @Scheduled(cron = "00 00 8-20 * * *")
    public void sendNotificationMessage() {
        String timing = DateTimeUtils.getNotificationTiming(LocalTime.now());

        List<RecordDto> forNotificationBotList = recordRepository.findAllByDateAndTiming(LocalDate.now(), timing).stream()
                .filter(element -> element.getClient().getChatId() != null)
                .map(recordMapper::toDTO)
                .toList();

        List<RecordDto> forNotificationEmailList = recordRepository.findAllByDateAndTiming(LocalDate.now(), timing).stream()
                .filter(element -> element.getClient().getEmail() != null)
                .map(recordMapper::toDTO)
                .toList();

        forNotificationBotList.parallelStream()
                .map(recordDto -> new SendMessage(recordDto.getClientDto().getChatId(), this.getAnswer(recordDto)))
                .forEach(telegramBot::sendMessage);

        forNotificationEmailList.parallelStream()
                .map(recordDto -> new EmailMessage(recordDto.getClientDto().getEmail(), this.getAnswer(recordDto)))
                .forEach(emailSender::sendEmail);
    }

    private String getAnswer(RecordDto recordDto) {
        return String.format(BotMessageEnum.NOTIFICATION.getMessage(),
                recordDto.getClientDto().getName(),
                recordDto.getPetServiceDto().getName(),
                recordDto.getMasterDto().getName(),
                recordDto.getPlaceDto().getName(),
                recordDto.getPlaceDto().getAddress(),
                recordDto.getTiming());
    }

}
