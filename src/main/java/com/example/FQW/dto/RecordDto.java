package com.example.FQW.dto;

import lombok.Data;

@Data
public class RecordDto {

    private Long id;

    private String daily;

    private String status;

    private ClientDto clientDto;

    private CalendarDto calendarDto;

    private MasterDto masterDto;

    private PlaceDto placeDto;

    private ServiceDto serviceDto;

}
