package com.example.FQW.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Schema(description = "услуга")
public class ServiceDto {

    private Long id;

    private String name;

    private Integer cost;

}
