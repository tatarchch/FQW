package com.example.fqw.services;

import com.example.fqw.dto.NotificationDto;
import com.example.fqw.dto.RecordDto;
import com.example.fqw.enums.BotMessageEnum;
import com.example.fqw.mapper.RecordMapper;
import com.example.fqw.repositories.RecordRepository;
import com.example.fqw.telegram.TelegramBot;
import com.example.fqw.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final RecordRepository recordRepository;
    private final RecordMapper recordMapper;
    private final TelegramBot telegramBot;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String kafkaTopicName;
    private final String kafkaMessId = String.valueOf(1);

    @Scheduled(cron = "${notification.cron}")
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

        if (!forNotificationEmailList.isEmpty()) {
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setRecords(forNotificationEmailList);
            kafkaTemplate.send(kafkaTopicName, kafkaMessId, notificationDto);
            log.info(forNotificationEmailList.toString());
        }

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
