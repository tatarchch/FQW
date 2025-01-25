package com.example.FQW.telegram.bot;

import com.example.FQW.service.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BotService {

    CalendarService calendarService;
    ClientService clientService;
    MasterService masterService;
    PlaceService placeService;
    RecordService recordService;
    ServiceService serviceService;

}
