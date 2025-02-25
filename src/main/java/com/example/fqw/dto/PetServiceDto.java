package com.example.fqw.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "услуга")
public class PetServiceDto {

    private Long id;

    private String name;

    private Integer cost;

}
