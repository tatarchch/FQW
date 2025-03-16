package com.example.fqw.telegram.keyboard;

import com.example.fqw.dto.CalendarDto;
import com.example.fqw.dto.MasterDto;
import com.example.fqw.dto.PlaceDto;
import com.example.fqw.dto.PetServiceDto;
import com.example.fqw.service.*;
import com.example.fqw.telegram.constant.ButtonNameEnum;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class InlineKeyboardService {

    PlaceService placeService;
    MasterService masterService;
    PetServiceService petServiceService;
    CalendarService calendarService;
    RecordService recordService;

    public InlineKeyboardMarkup getInlineMessageButtonsForPlace() {
        List<PlaceDto> placeList = placeService.getAll();

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> buttons = placeList.stream()
                .map(placeDto -> {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(placeDto.getName());
                    button.setCallbackData(ButtonNameEnum.GET_PLACE.name() + "/" + placeDto.getName());
                    return List.of(button);
                })
                .toList();

        inlineKeyboardMarkup.setKeyboard(buttons);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getInlineMessageButtonsForMaster(Long placeId) {
        List<MasterDto> masterList = masterService.getMastersByPlaceId(placeId);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> buttons = masterList.stream()
                .map(masterDto -> {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(masterDto.getName());
                    button.setCallbackData(ButtonNameEnum.GET_MASTER.name() + "/" + masterDto.getName());
                    return List.of(button);
                })
                .toList();

        inlineKeyboardMarkup.setKeyboard(buttons);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getInlineMessageButtonsForServices() {
        List<PetServiceDto> serviceList = petServiceService.getAll();

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> buttons = serviceList.stream()
                .map(serviceDto -> {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(serviceDto.getName());
                    button.setCallbackData(ButtonNameEnum.GET_SERVICE.name() + "/" + serviceDto.getName());
                    return List.of(button);
                })
                .toList();

        inlineKeyboardMarkup.setKeyboard(buttons);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getInlineMessageButtonsForServiceWithMaster(Long masterId) {
        List<PetServiceDto> serviceList = petServiceService.getServicesByMasterId(masterId);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> buttons = serviceList.stream()
                .map(serviceDto -> {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(serviceDto.getName());
                    button.setCallbackData(ButtonNameEnum.GET_SERVICE_BY_MASTER.name() + "/" + serviceDto.getName());
                    return List.of(button);
                })
                .toList();

        inlineKeyboardMarkup.setKeyboard(buttons);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getInlineMessageButtonsForMasterWithService(Long placeId, Long serviceId) {
        List<MasterDto> masterList = masterService.getMastersByPlaceIdAndServiceId(placeId, serviceId);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> buttons = masterList.stream()
                .map(masterDto -> {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(masterDto.getName());
                    button.setCallbackData(ButtonNameEnum.GET_MASTER_BY_SERVICE.name() + "/" + masterDto.getName());
                    return List.of(button);
                })
                .toList();

        inlineKeyboardMarkup.setKeyboard(buttons);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getInlineMessageButtonsForDate(Long masterId) {
        //List<CalendarDto> calendarList = calendarService.getCalendarsByMasterInAndDateGreaterThanEqual(masterId, LocalDate.now());
        List<CalendarDto> calendarList = calendarService.getCalendarsByMasterInAndDateGreaterThanEqual(masterId);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> buttons = calendarList.stream()
                .map(calendarDto -> {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(calendarDto.getDate().toString());
                    button.setCallbackData(ButtonNameEnum.GET_DATE.name() + "/" + calendarDto.getId());
                    return List.of(button);
                })
                .toList();

        inlineKeyboardMarkup.setKeyboard(buttons);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getInlineMessageButtonsForTiming(Long masterId, LocalDate date) {
        List<String> timingList = recordService.getTimingsByMasterAndDate(masterId, date);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> buttons = timingList.stream()
                .map(timing -> {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(timing);
                    button.setCallbackData(ButtonNameEnum.GET_TIME.name() + "/" + timing);
                    return List.of(button);
                })
                .toList();

        inlineKeyboardMarkup.setKeyboard(buttons);
        return inlineKeyboardMarkup;
    }

}
