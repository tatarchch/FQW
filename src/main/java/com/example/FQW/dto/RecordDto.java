package com.example.FQW.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordDto {

    private Long id;

    private String timing;

    private LocalDate date;

    private String status;

    private ClientDto clientDto;

    private MasterDto masterDto;

    private PlaceDto placeDto;

    private ServiceDto serviceDto;

}
