package com.example.FQW.service;

import com.example.FQW.repositories.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Setter
@Getter
@AllArgsConstructor
public class RecordService {

    private CalendarRepository calendarRepository;
    private ClientRepositiry clientRepositiry;
    private MasterRepository masterRepository;
    private RecordRepository recordRepository;
    private ServiceRepository serviceRepository;
    private PlaceRepository placeRepository;


}
