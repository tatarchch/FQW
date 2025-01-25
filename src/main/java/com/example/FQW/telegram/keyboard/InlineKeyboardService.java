package com.example.FQW.telegram.keyboard;

import com.example.FQW.dto.CalendarDto;
import com.example.FQW.dto.MasterDto;
import com.example.FQW.dto.PlaceDto;
import com.example.FQW.dto.ServiceDto;
import com.example.FQW.telegram.bot.BotService;
import com.example.FQW.telegram.utils.ConverterDataToKeyboardUtils;
import com.example.FQW.telegram.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InlineKeyboardService {

    private final BotService botService;

    /*public InlineKeyboardMarkup getInlineMessageButtonsForPlace() {
        List<PlaceDto> places = botService.getPlaceService().getAll();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> rowInline = places.stream()
                .map(place -> {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(place.getName());
                    button.setCallbackData(ButtonNameEnum.GET_PLACE.name() + "/" + place.getName());
                    return button;
                })
                .toList();
        inlineKeyboardMarkup.setKeyboard(List.of(rowInline));

        return inlineKeyboardMarkup;
    }*/

    public InlineKeyboardMarkup getInlineMessageButtonsForPlace() {
        List<PlaceDto> places = botService.getPlaceService().getAll();
        return ConverterDataToKeyboardUtils.convertPlaces(places);
    }

    /*public InlineKeyboardMarkup getInlineMessageButtonsForMaster(Long placeId) {
        List<MasterDto> masters = botService.getMasterService()
                .getMastersByPlaceId(placeId);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> rowInline = masters.stream()
                .map(masterDto -> {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(masterDto.getName());
                    button.setCallbackData(ButtonNameEnum.GET_MASTER.name() + "/" + masterDto.getName());
                    return button;
                })
                .toList();
        inlineKeyboardMarkup.setKeyboard(List.of(rowInline));

        return inlineKeyboardMarkup;
    }*/

    public InlineKeyboardMarkup getInlineMessageButtonsForMaster(Long placeId) {
        List<MasterDto> masters = botService.getMasterService()
                .getMastersByPlaceId(placeId);
        return ConverterDataToKeyboardUtils.convertMasters(masters);
    }

    /*public InlineKeyboardMarkup getInlineMessageButtonsForServices() {
        List<ServiceDto> services = botService.getServiceService()
                .getAll();

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> rowInline = services.stream()
                .map(serviceDto -> {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(serviceDto.getName());
                    button.setCallbackData(ButtonNameEnum.GET_SERVICE.name() + "/" + serviceDto.getName());
                    return button;
                })
                .toList();
        inlineKeyboardMarkup.setKeyboard(List.of(rowInline));

        return inlineKeyboardMarkup;
    }*/

    public InlineKeyboardMarkup getInlineMessageButtonsForServices() {
        List<ServiceDto> services = botService.getServiceService()
                .getAll();
        return ConverterDataToKeyboardUtils.convertServices(services);
    }

    /*public InlineKeyboardMarkup getInlineMessageButtonsForServiceWithMaster(Long masterId) {
        List<ServiceDto> service = botService.getServiceService()
                .getServicesByMasterId(masterId);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> rowInline = service.stream()
                .map(serviceDto -> {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(serviceDto.getName());
                    button.setCallbackData(ButtonNameEnum.GET_SERVICE_BY_MASTER.name() + "/" + serviceDto.getName());
                    return button;
                })
                .toList();
        inlineKeyboardMarkup.setKeyboard(List.of(rowInline));

        return inlineKeyboardMarkup;
    }*/

    public InlineKeyboardMarkup getInlineMessageButtonsForServiceWithMaster(Long masterId) {
        List<ServiceDto> services = botService.getServiceService()
                .getServicesByMasterId(masterId);
        return ConverterDataToKeyboardUtils.convertServicesWithMaster(services);
    }

    /*public InlineKeyboardMarkup getInlineMessageButtonsForMasterWithService(Long placeId, Long serviceId) {
        List<MasterDto> masters = botService.getMasterService()
                .getMastersByPlaceIdAndServiceId(placeId, serviceId);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> rowInline = masters.stream()
                .map(masterDto -> {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(masterDto.getName());
                    button.setCallbackData(ButtonNameEnum.GET_MASTER_BY_SERVICE.name() + "/" + masterDto.getName());
                    return button;
                })
                .toList();
        inlineKeyboardMarkup.setKeyboard(List.of(rowInline));

        return inlineKeyboardMarkup;
    }*/

    public InlineKeyboardMarkup getInlineMessageButtonsForMasterWithService(Long placeId, Long serviceId) {
        List<MasterDto> masters = botService.getMasterService()
                .getMastersByPlaceIdAndServiceId(placeId, serviceId);
        return ConverterDataToKeyboardUtils.convertMastersWithService(masters);
    }

    /*public InlineKeyboardMarkup getInlineMessageButtonsForDate(Long masterId) {
        List<CalendarDto> calendars = botService.getCalendarService()
                .getCalendarsByMasterInAndDateGreaterThanEqual(masterId, LocalDate.now());

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> rowInline = calendars.stream()
                .map(calendarDto -> {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(calendarDto.getDate().toString());
                    button.setCallbackData(ButtonNameEnum.GET_DATE.name() + "/" + calendarDto.getId());
                    return button;
                })
                .toList();
        inlineKeyboardMarkup.setKeyboard(List.of(rowInline));

        return inlineKeyboardMarkup;
    }*/

    public InlineKeyboardMarkup getInlineMessageButtonsForDate(Long masterId) {
        List<CalendarDto> calendars = botService.getCalendarService()
                .getCalendarsByMasterInAndDateGreaterThanEqual(masterId, LocalDate.now());

        return ConverterDataToKeyboardUtils.convertDates(calendars);
    }

    /*public InlineKeyboardMarkup getInlineMessageButtonsForDaily(Long masterId, LocalDate date) {
        List<String> dailyList = botService.getRecordService().
                getDailyByMasterAndDate(masterId, date);

        List<String> freeDailyList = DateTimeUtils.DAILY_SET.stream()
                .filter(daily -> !dailyList.contains(daily))
                .filter(daily -> !LocalDate.now().equals(date) || DateTimeUtils.isTodayAvailableTime(daily))
                .toList();

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> rowInline = freeDailyList.stream()
                .map(daily -> {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(daily);
                    button.setCallbackData(ButtonNameEnum.GET_TIME.name() + "/" + daily);
                    return button;
                })
                .toList();

        inlineKeyboardMarkup.setKeyboard(rowInline.isEmpty() ? Collections.emptyList() : List.of(rowInline));

        return inlineKeyboardMarkup;
    }*/

    public InlineKeyboardMarkup getInlineMessageButtonsForTiming(Long masterId, LocalDate date) {
        List<String> dailyList = botService.getRecordService().
                getDailyByMasterAndDate(masterId, date);

        List<String> freeDailyList = DateTimeUtils.DAILY_SET.stream()
                .filter(daily -> !dailyList.contains(daily))
                .filter(daily -> !LocalDate.now().equals(date) || DateTimeUtils.isTodayAvailableTime(daily))
                .toList();

        return ConverterDataToKeyboardUtils.convertTimings(freeDailyList);
    }

}
