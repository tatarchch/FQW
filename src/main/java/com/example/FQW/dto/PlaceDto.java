package com.example.FQW.dto;

import com.example.FQW.controller.CalendarController;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PlaceDto {

    private Long id;

    private String name;

    private String address;

}
