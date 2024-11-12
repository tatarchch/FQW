package com.example.FQW.service;

import com.example.FQW.dto.PreOrderDto;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Getter
public class PreOrderService {

    private final ConcurrentHashMap<String, PreOrderDto> preOrderMap = new ConcurrentHashMap<>();

    public PreOrderDto callClient(Long clientId) {
        String correlationId = UUID.randomUUID().toString();
        PreOrderDto preOrderDto = new PreOrderDto();
        preOrderDto.setClientId(clientId);
        preOrderDto.setCorrelationId(correlationId);
        preOrderMap.put(correlationId, preOrderDto);
        return preOrderDto;
    }

    public PreOrderDto callPlace(Long placeId, String correlationId) {
        PreOrderDto preOrderDto = preOrderMap.get(correlationId);
        preOrderDto.setPlaceId(placeId);
        preOrderMap.put(correlationId ,preOrderDto);
        return preOrderDto;
    }

    public PreOrderDto callMaster(Long masterId, String correlationId) {
        PreOrderDto preOrderDto = preOrderMap.get(correlationId);
        preOrderDto.setMasterId(masterId);
        preOrderMap.put(correlationId ,preOrderDto);
        return preOrderDto;
    }

    public PreOrderDto callService(Long serviceId, String correlationId) {
        PreOrderDto preOrderDto = preOrderMap.get(correlationId);
        preOrderDto.setServiceId(serviceId);
        preOrderMap.put(correlationId ,preOrderDto);
        return preOrderDto;
    }

    public PreOrderDto callCalendar(Long calendarId, String correlationId) {
        PreOrderDto preOrderDto = preOrderMap.get(correlationId);
        preOrderDto.setCalendarId(calendarId);
        preOrderMap.put(correlationId ,preOrderDto);
        return preOrderDto;
    }

    public void submitRecord(String correlationId) {
        // вызов рекорда для создания и сохранения
        //...
        preOrderMap.remove(correlationId);
    }

}
