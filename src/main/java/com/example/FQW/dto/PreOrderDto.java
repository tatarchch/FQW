package com.example.FQW.dto;

import lombok.Data;

@Data
public class PreOrderDto {

    private String correlationId;

    private Long clientId;

    private Long calendarId;

    private Long masterId;

    private Long placeId;

    private Long serviceId;

}
