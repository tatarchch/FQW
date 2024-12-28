package com.example.FQW.service;

import com.example.FQW.dto.PreOrderDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@Getter
public class PreOrderService {

    private final ConcurrentHashMap<String, PreOrderDto> preOrderMap = new ConcurrentHashMap<>();

    public PreOrderDto callClient(Long clientId) {
        String correlationId = UUID.randomUUID().toString();
        PreOrderDto preOrderDto = new PreOrderDto();
        preOrderDto.setClientId(clientId);
        preOrderDto.setCorrelationId(correlationId);
        return preOrderDto;
    }


    public PreOrderDto callPlace(Long placeId, PreOrderDto preOrderDto) {
        preOrderDto.setPlaceId(placeId);
        return preOrderDto;
    }

    public PreOrderDto callMaster(Long masterId, PreOrderDto preOrderDto) {
        preOrderDto.setMasterId(masterId);
        return preOrderDto;
    }

    public PreOrderDto callService(Long serviceId, PreOrderDto preOrderDto) {
        preOrderDto.setServiceId(serviceId);
        return preOrderDto;
    }

    public PreOrderDto callCalendar(Long calendarId, PreOrderDto preOrderDto) {
        preOrderDto.setCalendarId(calendarId);
        return preOrderDto;
    }

    public void submitRecord(String correlationId) {
        // вызов рекорда для создания и сохранения
        //...
        preOrderMap.remove(correlationId);
    }

}
