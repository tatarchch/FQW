package com.example.FQW.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class MasterDto {

    private Long id;

    private String name;

    private String surname;

    private String patronymic;

    private Integer level;

}
