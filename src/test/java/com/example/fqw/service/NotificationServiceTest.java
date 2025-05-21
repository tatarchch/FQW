package com.example.fqw.service;

import com.example.fqw.dto.*;
import com.example.fqw.entity.Client;
import com.example.fqw.entity.Master;
import com.example.fqw.entity.PetService;
import com.example.fqw.entity.Record;
import com.example.fqw.mapper.RecordMapperImpl;
import com.example.fqw.repositories.RecordRepository;
import com.example.fqw.services.NotificationService;
import com.example.fqw.telegram.TelegramBot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {
    @Mock
    private RecordRepository recordRepository;
    @Mock
    private RecordMapperImpl recordMapper;
    @Mock
    private TelegramBot telegramBot;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void sendNotificationMessage() {
        var nowTiming = "10:00-11:00";

        var record1 = this.getRecord(nowTiming, "1");
        var record2 = this.getRecord(nowTiming, "2");

        var recordDto1 = this.getRecordDto("1", "1", "1", "1", "1", "address1", nowTiming);
        var recordDto2 = this.getRecordDto("2", "2", "2", "2", "2", "address2", nowTiming);

        when(recordRepository.findAllByDateAndTiming(eq(LocalDate.now()), any(String.class))).thenReturn(List.of(record1, record2));
        when(recordMapper.toDTO(record1)).thenReturn(recordDto1);
        when(recordMapper.toDTO(record2)).thenReturn(recordDto2);

        notificationService.sendNotificationMessage();

        verify(telegramBot, times(2)).sendMessage(any(SendMessage.class));
    }

    private RecordDto getRecordDto(String chatId,
                                   String clientName,
                                   String serviceName,
                                   String masterName,
                                   String placeName,
                                   String placeAddress,
                                   String timingForNotification) {

        var clientDto = new ClientDto();
        clientDto.setChatId(chatId);
        clientDto.setName(clientName);

        var serviceDto = new PetServiceDto();
        serviceDto.setName(serviceName);

        var masterDto = new MasterDto();
        masterDto.setName(masterName);

        var placeDto = new PlaceDto();
        placeDto.setName(placeName);
        placeDto.setAddress(placeAddress);

        var recordDto = new RecordDto();
        recordDto.setTiming(timingForNotification);
        recordDto.setClientDto(clientDto);
        recordDto.setPetServiceDto(serviceDto);
        recordDto.setMasterDto(masterDto);
        recordDto.setPlaceDto(placeDto);

        return recordDto;
    }

    private Record getRecord(String timing, String chatId) {
        var client = new Client();
        client.setChatId(chatId);

        var record = new Record();
        record.setTiming(timing);
        record.setClient(client);
        record.setMaster(new Master());
        record.setPetService(new PetService());

        return record;
    }
}