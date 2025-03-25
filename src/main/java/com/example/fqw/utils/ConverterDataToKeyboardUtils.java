package com.example.fqw.utils;

import com.example.fqw.dto.CalendarDto;
import com.example.fqw.dto.MasterDto;
import com.example.fqw.dto.PetServiceDto;
import com.example.fqw.dto.PlaceDto;
import com.example.fqw.enums.ButtonNameEnum;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;
import java.util.List;

@UtilityClass
@Deprecated
public class ConverterDataToKeyboardUtils {

    public InlineKeyboardMarkup convertPlaces(List<PlaceDto> places) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        if (places.size() <= 2) {
            List<InlineKeyboardButton> rowInline = places.stream()
                    .map(placeDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(placeDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_PLACE.name() + "/" + placeDto.getName());
                        return button;
                    })
                    .toList();

            inlineKeyboardMarkup.setKeyboard(List.of(rowInline));

        } else if (places.size() > 2 && places.size() <= 4) {
            List<PlaceDto> places1 = places.subList(0, places.size() / 2);
            List<PlaceDto> places2 = places.subList(places.size() / 2, places.size());

            List<InlineKeyboardButton> rowInline1 = places1.stream()
                    .map(placeDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(placeDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_PLACE.name() + "/" + placeDto.getName());
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline2 = places2.stream()
                    .map(placeDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(placeDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_PLACE.name() + "/" + placeDto.getName());
                        return button;
                    })
                    .toList();

            inlineKeyboardMarkup.setKeyboard(List.of(rowInline1, rowInline2));
        } else if (places.size() > 4) {
            List<PlaceDto> places1 = places.subList(0, places.size() / 3);
            List<PlaceDto> places2 = places.subList(places.size() / 3, places.size() / 2 * 3);
            List<PlaceDto> places3 = places.subList(places.size() / 3 * 2, places.size());

            List<InlineKeyboardButton> rowInline1 = places1.stream()
                    .map(placeDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(placeDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_PLACE.name() + "/" + placeDto.getName());
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline2 = places2.stream()
                    .map(placeDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(placeDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_PLACE.name() + "/" + placeDto.getName());
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline3 = places3.stream()
                    .map(placeDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(placeDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_PLACE.name() + "/" + placeDto.getName());
                        return button;
                    })
                    .toList();

            inlineKeyboardMarkup.setKeyboard(List.of(rowInline1, rowInline2, rowInline3));
        }
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup convertServices(List<PetServiceDto> services) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        if (services.size() <= 3) {
            List<InlineKeyboardButton> rowInline = services.stream()
                    .map(serviceDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(serviceDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_SERVICE.name() + "/" + serviceDto.getName());
                        return button;
                    })
                    .toList();
            inlineKeyboardMarkup.setKeyboard(List.of(rowInline));
        } else if (services.size() > 3 && services.size() <= 6) {
            List<PetServiceDto> service1 = services.subList(0, services.size() / 2);
            List<PetServiceDto> service2 = services.subList(services.size() / 2, services.size());

            List<InlineKeyboardButton> rowInline1 = service1.stream().sorted()
                    .map(serviceDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(serviceDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_SERVICE.name() + "/" + serviceDto.getName());
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline2 = service2.stream()
                    .map(serviceDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(serviceDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_SERVICE.name() + "/" + serviceDto.getName());
                        return button;
                    })
                    .toList();

            inlineKeyboardMarkup.setKeyboard(List.of(rowInline1, rowInline2));
        } else if (services.size() > 6 && services.size() <= 9) {
            List<PetServiceDto> service1 = services.subList(0, services.size() / 3);
            List<PetServiceDto> service2 = services.subList(services.size() / 3, services.size() / 2 * 3);
            List<PetServiceDto> service3 = services.subList(services.size() / 3 * 2, services.size());

            List<InlineKeyboardButton> rowInline1 = service1.stream()
                    .map(serviceDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(serviceDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_SERVICE.name() + "/" + serviceDto.getName());
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline2 = service2.stream()
                    .map(serviceDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(serviceDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_SERVICE.name() + "/" + serviceDto.getName());
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline3 = service3.stream()
                    .map(serviceDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(serviceDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_SERVICE.name() + "/" + serviceDto.getName());
                        return button;
                    })
                    .toList();

            inlineKeyboardMarkup.setKeyboard(List.of(rowInline1, rowInline2, rowInline3));
        }
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup convertServicesWithMaster(List<PetServiceDto> services) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        if (services.size() <= 3) {
            List<InlineKeyboardButton> rowInline = services.stream()
                    .map(serviceDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(serviceDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_SERVICE_BY_MASTER.name() + "/" + serviceDto.getName());
                        return button;
                    })
                    .toList();
            inlineKeyboardMarkup.setKeyboard(List.of(rowInline));
        } else if (services.size() > 3 && services.size() <= 6) {
            List<PetServiceDto> service1 = services.subList(0, services.size() / 2);
            List<PetServiceDto> service2 = services.subList(services.size() / 2, services.size());

            List<InlineKeyboardButton> rowInline1 = service1.stream().sorted()
                    .map(serviceDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(serviceDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_SERVICE_BY_MASTER.name() + "/" + serviceDto.getName());
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline2 = service2.stream()
                    .map(serviceDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(serviceDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_SERVICE_BY_MASTER.name() + "/" + serviceDto.getName());
                        return button;
                    })
                    .toList();

            inlineKeyboardMarkup.setKeyboard(List.of(rowInline1, rowInline2));
        } else if (services.size() > 6 && services.size() <= 9) {
            List<PetServiceDto> service1 = services.subList(0, services.size() / 3);
            List<PetServiceDto> service2 = services.subList(services.size() / 3, services.size() / 2 * 3);
            List<PetServiceDto> service3 = services.subList(services.size() / 3 * 2, services.size());

            List<InlineKeyboardButton> rowInline1 = service1.stream()
                    .map(serviceDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(serviceDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_SERVICE_BY_MASTER.name() + "/" + serviceDto.getName());
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline2 = service2.stream()
                    .map(serviceDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(serviceDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_SERVICE_BY_MASTER.name() + "/" + serviceDto.getName());
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline3 = service3.stream()
                    .map(serviceDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(serviceDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_SERVICE_BY_MASTER.name() + "/" + serviceDto.getName());
                        return button;
                    })
                    .toList();

            inlineKeyboardMarkup.setKeyboard(List.of(rowInline1, rowInline2, rowInline3));
        }
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup convertMasters(List<MasterDto> masters) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        if (masters.size() <= 3) {
            List<InlineKeyboardButton> rowInline = masters.stream()
                    .map(masterDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(masterDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_MASTER.name() + "/" + masterDto.getName());
                        return button;
                    })
                    .toList();

            inlineKeyboardMarkup.setKeyboard(List.of(rowInline));

        } else if (masters.size() > 3 && masters.size() <= 6) {
            List<MasterDto> masters1 = masters.subList(0, masters.size() / 2);
            List<MasterDto> masters2 = masters.subList(masters.size() / 2, masters.size());

            List<InlineKeyboardButton> rowInline1 = masters1.stream()
                    .map(masterDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(masterDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_MASTER.name() + "/" + masterDto.getName());
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline2 = masters2.stream()
                    .map(masterDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(masterDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_MASTER.name() + "/" + masterDto.getName());
                        return button;
                    })
                    .toList();

            inlineKeyboardMarkup.setKeyboard(List.of(rowInline1, rowInline2));
        } else if (masters.size() > 6) {
            List<MasterDto> masters1 = masters.subList(0, masters.size() / 3);
            List<MasterDto> masters2 = masters.subList(masters.size() / 3, masters.size() / 2 * 3);
            List<MasterDto> masters3 = masters.subList(masters.size() / 3 * 2, masters.size());

            List<InlineKeyboardButton> rowInline1 = masters1.stream()
                    .map(masterDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(masterDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_MASTER.name() + "/" + masterDto.getName());
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline2 = masters2.stream()
                    .map(masterDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(masterDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_MASTER.name() + "/" + masterDto.getName());
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline3 = masters3.stream()
                    .map(masterDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(masterDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_MASTER.name() + "/" + masterDto.getName());
                        return button;
                    })
                    .toList();

            inlineKeyboardMarkup.setKeyboard(List.of(rowInline1, rowInline2, rowInline3));
        }
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup convertMastersWithService(List<MasterDto> masters) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        if (masters.size() <= 3) {
            List<InlineKeyboardButton> rowInline = masters.stream()
                    .map(masterDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(masterDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_MASTER_BY_SERVICE.name() + "/" + masterDto.getName());
                        return button;
                    })
                    .toList();

            inlineKeyboardMarkup.setKeyboard(List.of(rowInline));

        } else if (masters.size() > 3 && masters.size() <= 6) {
            List<MasterDto> masters1 = masters.subList(0, masters.size() / 2);
            List<MasterDto> masters2 = masters.subList(masters.size() / 2, masters.size());

            List<InlineKeyboardButton> rowInline1 = masters1.stream()
                    .map(masterDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(masterDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_MASTER_BY_SERVICE.name() + "/" + masterDto.getName());
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline2 = masters2.stream()
                    .map(masterDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(masterDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_MASTER_BY_SERVICE.name() + "/" + masterDto.getName());
                        return button;
                    })
                    .toList();

            inlineKeyboardMarkup.setKeyboard(List.of(rowInline1, rowInline2));
        } else if (masters.size() > 6) {
            List<MasterDto> masters1 = masters.subList(0, masters.size() / 3);
            List<MasterDto> masters2 = masters.subList(masters.size() / 3, masters.size() / 2 * 3);
            List<MasterDto> masters3 = masters.subList(masters.size() / 3 * 2, masters.size());

            List<InlineKeyboardButton> rowInline1 = masters1.stream()
                    .map(masterDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(masterDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_MASTER_BY_SERVICE.name() + "/" + masterDto.getName());
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline2 = masters2.stream()
                    .map(masterDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(masterDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_MASTER_BY_SERVICE.name() + "/" + masterDto.getName());
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline3 = masters3.stream()
                    .map(masterDto -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(masterDto.getName());
                        button.setCallbackData(ButtonNameEnum.GET_MASTER_BY_SERVICE.name() + "/" + masterDto.getName());
                        return button;
                    })
                    .toList();

            inlineKeyboardMarkup.setKeyboard(List.of(rowInline1, rowInline2, rowInline3));
        }
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup convertDates(List<CalendarDto> calendars) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = calendars.stream()
                .map(
                        calendarDto ->
                        {
                            InlineKeyboardButton button = new InlineKeyboardButton();
                            button.setText(calendarDto.getDate().toString());
                            button.setCallbackData(ButtonNameEnum.GET_DATE.name() + "/" + calendarDto.getId());
                            return List.of(button);
                        }
                ).toList();
        inlineKeyboardMarkup.setKeyboard(buttons);

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup convertTimings(List<String> timings) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<String> sortedTimings = timings.stream().sorted().toList();

        if (sortedTimings.isEmpty()) {
            inlineKeyboardMarkup.setKeyboard(Collections.emptyList());

        } else if (sortedTimings.size() <= 3) {
            List<InlineKeyboardButton> rowInline = sortedTimings.stream()
                    .map(timing -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(timing);
                        button.setCallbackData(ButtonNameEnum.GET_TIME.name() + "/" + timing);
                        return button;
                    })
                    .toList();

            inlineKeyboardMarkup.setKeyboard(List.of(rowInline));

        } else if (sortedTimings.size() > 3 && sortedTimings.size() <= 6) {
            List<String> timing1 = sortedTimings.subList(0, (int) Math.ceil(sortedTimings.size() / 2.0));
            List<String> timing2 = sortedTimings.subList((int) Math.ceil(sortedTimings.size() / 2.0), sortedTimings.size());

            List<InlineKeyboardButton> rowInline1 = timing1.stream()
                    .map(timing -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(timing);
                        button.setCallbackData(ButtonNameEnum.GET_TIME.name() + "/" + timing);
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline2 = timing2.stream()
                    .map(timing -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(timing);
                        button.setCallbackData(ButtonNameEnum.GET_TIME.name() + "/" + timing);
                        return button;
                    })
                    .toList();

            inlineKeyboardMarkup.setKeyboard(List.of(rowInline1, rowInline2));

        } else if (sortedTimings.size() > 6 && sortedTimings.size() <= 9) {
            List<String> timings1 = sortedTimings.subList(0, (int) Math.ceil(sortedTimings.size() / 3.0));
            List<String> timings2 = sortedTimings.subList((int) Math.ceil(sortedTimings.size() / 3.0), (int) Math.ceil(sortedTimings.size() / 3.0 * 2.0));
            List<String> timings3 = sortedTimings.subList((int) Math.ceil(sortedTimings.size() / 3.0 * 2.0), sortedTimings.size());

            List<InlineKeyboardButton> rowInline1 = timings1.stream()
                    .map(timing -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(timing);
                        button.setCallbackData(ButtonNameEnum.GET_TIME.name() + "/" + timing);
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline2 = timings2.stream()
                    .map(timing -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(timing);
                        button.setCallbackData(ButtonNameEnum.GET_TIME.name() + "/" + timing);
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline3 = timings3.stream()
                    .map(timing -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(timing);
                        button.setCallbackData(ButtonNameEnum.GET_TIME.name() + "/" + timing);
                        return button;
                    })
                    .toList();

            inlineKeyboardMarkup.setKeyboard(List.of(rowInline1, rowInline2, rowInline3));
        } else if (sortedTimings.size() > 9) {
            List<String> timings1 = sortedTimings.subList(0, (int) Math.ceil(sortedTimings.size() / 4.0));
            List<String> timings2 = sortedTimings.subList((int) Math.ceil(sortedTimings.size() / 4.0), (int) Math.ceil(sortedTimings.size() / 2.0));
            List<String> timings3 = sortedTimings.subList((int) Math.ceil(sortedTimings.size() / 2.0), (int) Math.ceil(sortedTimings.size() / 4.0 * 3.0));
            List<String> timings4 = sortedTimings.subList((int) Math.ceil(sortedTimings.size() / 4.0 * 3.0), sortedTimings.size());

            List<InlineKeyboardButton> rowInline1 = timings1.stream()
                    .map(timing -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(timing);
                        button.setCallbackData(ButtonNameEnum.GET_TIME.name() + "/" + timing);
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline2 = timings2.stream()
                    .map(timing -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(timing);
                        button.setCallbackData(ButtonNameEnum.GET_TIME.name() + "/" + timing);
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline3 = timings3.stream()
                    .map(timing -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(timing);
                        button.setCallbackData(ButtonNameEnum.GET_TIME.name() + "/" + timing);
                        return button;
                    })
                    .toList();

            List<InlineKeyboardButton> rowInline4 = timings4.stream()
                    .map(timing -> {
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(timing);
                        button.setCallbackData(ButtonNameEnum.GET_TIME.name() + "/" + timing);
                        return button;
                    })
                    .toList();
            inlineKeyboardMarkup.setKeyboard(List.of(rowInline1, rowInline2, rowInline3, rowInline4));
        }
        return inlineKeyboardMarkup;
    }
}
