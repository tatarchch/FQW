package com.example.fqw.telegram.handler;

import com.example.fqw.dto.*;
import com.example.fqw.enums.BotMessageEnum;
import com.example.fqw.enums.ButtonNameEnum;
import com.example.fqw.services.CalendarService;
import com.example.fqw.services.MasterService;
import com.example.fqw.services.PetServiceService;
import com.example.fqw.services.PlaceService;
import com.example.fqw.telegram.keyboard.InlineKeyboardService;
import com.example.fqw.telegram.keyboard.ReplyKeyboardService;
import com.example.fqw.utils.LogUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CallbackQueryHandler {

    CalendarService calendarService;
    MasterService masterService;
    PlaceService placeService;
    PetServiceService petServiceService;

    InlineKeyboardService inlineKeyboardService;
    ReplyKeyboardService replyKeyboardService;

    public SendMessage answerCallbackQuery(CallbackQuery buttonQuery, Map<String, RecordDto> recordMap) {
        String userName = buttonQuery.getFrom().getUserName();
        String chatId = String.valueOf(buttonQuery.getMessage().getChatId());
        String[] queryData = buttonQuery.getData().split("/");

        ButtonNameEnum command = ButtonNameEnum.valueOf(queryData[0]);
        String data = queryData[1];

        SendMessage sendMessage = new SendMessage();

        switch (command) {
            case GET_PLACE -> sendMessage = this.getPlaceAnswer(chatId, userName, data, recordMap);
            case GET_MASTER -> sendMessage = this.getMasterAnswer(chatId, userName, data, recordMap);
            case GET_SERVICE_BY_MASTER ->
                    sendMessage = this.getServiceWithMasterAnswer(chatId, userName, data, recordMap);
            case GET_SERVICE -> sendMessage = this.getServiceAnswer(chatId, userName, data, recordMap);
            case GET_MASTER_BY_SERVICE ->
                    sendMessage = this.getMasterWithServiceAnswer(chatId, userName, data, recordMap);
            case GET_DATE -> sendMessage = this.getDateAnswer(chatId, userName, data, recordMap);
            case GET_TIME -> sendMessage = this.getTimingAnswer(chatId, userName, data, recordMap);
            default -> {
                sendMessage.setChatId(chatId);
                sendMessage.setText(BotMessageEnum.EXCEPTION_WHAT_THE_FUCK.getMessage());
                LogUtils.getErrorLogForInlineKeyboard();
            }
        }
        return sendMessage;
    }

    private SendMessage getPlaceAnswer(String chatId, String userName, String data, Map<String, RecordDto> recordMap) {
        PlaceDto placeDto = placeService.getPlaceById(Long.valueOf(data));

        RecordDto recordDto = recordMap.get(userName);
        recordDto.setPlaceDto(placeDto);
        recordMap.put(data, recordDto);

        LogUtils.getPlaceLog(userName, placeDto);

        SendMessage sendMessage = new SendMessage(chatId, String.format(BotMessageEnum.PLACE.getMessage(),
                placeDto.getName(), placeDto.getAddress()));

        sendMessage.setReplyMarkup(replyKeyboardService.getMasterServiceMenuKeyboard());

        return sendMessage;
    }

    private SendMessage getMasterAnswer(String chatId, String userName, String data, Map<String, RecordDto> recordMap) {
        MasterDto masterDto = masterService.getMasterById(Long.valueOf(data));

        RecordDto recordDto = recordMap.get(userName);
        recordDto.setMasterDto(masterDto);
        recordMap.put(data, recordDto);

        LogUtils.getMasterLog(userName, masterDto);

        SendMessage sendMessage = new SendMessage(chatId, String.format(BotMessageEnum.MASTER.getMessage(),
                masterDto.getName(), masterDto.getLevel()));

        sendMessage.setReplyMarkup(replyKeyboardService.getServiceWithMasterMenuKeyboard());

        return sendMessage;
    }

    private SendMessage getServiceAnswer(String chatId, String userName, String data, Map<String, RecordDto> recordMap) {
        PetServiceDto petServiceDto = petServiceService.getServiceById(Long.valueOf(data));

        RecordDto recordDto = recordMap.get(userName);
        recordDto.setPetServiceDto(petServiceDto);
        recordMap.put(data, recordDto);

        LogUtils.getServiceLog(userName, petServiceDto);

        SendMessage sendMessage = new SendMessage(chatId, String.format(BotMessageEnum.SERVICE.getMessage(),
                petServiceDto.getName(), petServiceDto.getCost()));

        sendMessage.setReplyMarkup(replyKeyboardService.getMasterWithServiceMenuKeyboard());

        return sendMessage;
    }

    public SendMessage getServiceWithMasterAnswer(String chatId, String userName, String data, Map<String, RecordDto> recordMap) {
        PetServiceDto petServiceDto = petServiceService.getServiceById(Long.valueOf(data));

        RecordDto recordDto = recordMap.get(userName);
        recordDto.setPetServiceDto(petServiceDto);
        recordMap.put(data, recordDto);
        LogUtils.getServiceLog(userName, petServiceDto);

        SendMessage sendMessage = new SendMessage(chatId, String.format(BotMessageEnum.SERVICE.getMessage(),
                petServiceDto.getName(), petServiceDto.getCost()));

        sendMessage.setReplyMarkup(replyKeyboardService.getDateMenuKeyboard());

        return sendMessage;
    }

    public SendMessage getMasterWithServiceAnswer(String chatId, String userName, String data, Map<String, RecordDto> recordMap) {
        MasterDto masterDto = masterService.getMasterById(Long.valueOf(data));

        RecordDto recordDto = recordMap.get(userName);
        recordDto.setMasterDto(masterDto);
        recordMap.put(data, recordDto);

        LogUtils.getMasterLog(userName, masterDto);

        SendMessage sendMessage = new SendMessage(chatId, String.format(BotMessageEnum.MASTER.getMessage(),
                masterDto.getName(), masterDto.getLevel()));

        sendMessage.setReplyMarkup(replyKeyboardService.getDateMenuKeyboard());

        return sendMessage;
    }

    public SendMessage getDateAnswer(String chatId, String userName, String data, Map<String, RecordDto> recordMap) {
        CalendarDto calendarDto = calendarService.getCalendarById(Long.valueOf(data));

        RecordDto recordDto = recordMap.get(userName);
        recordDto.setDate(calendarDto.getDate());
        recordMap.put(data, recordDto);

        Long masterId = recordDto.getMasterDto().getId();

        LogUtils.getDateLog(userName, calendarDto);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        InlineKeyboardMarkup inlineKeyboardMarkup = inlineKeyboardService.getInlineMessageButtonsForTiming(masterId, calendarDto.getDate());

        sendMessage.setText(inlineKeyboardMarkup.getKeyboard().isEmpty() ?
                BotMessageEnum.NO_TIME.getMessage() : String.format(BotMessageEnum.DATE.getMessage(), calendarDto.getDate().toString()));

        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        return sendMessage;
    }

    public SendMessage getTimingAnswer(String chatId, String userName, String data, Map<String, RecordDto> recordMap) {
        RecordDto recordDto = recordMap.get(userName);
        recordDto.setTiming(data);
        recordMap.put(data, recordDto);

        LogUtils.getTimeLog(userName, recordDto);

        SendMessage sendMessage = new SendMessage(chatId,
                String.format(BotMessageEnum.RECORD.getMessage(),
                        recordDto.getTiming(),
                        recordDto.getDate(),
                        recordDto.getMasterDto().getName(),
                        recordDto.getPlaceDto().getName(),
                        recordDto.getPlaceDto().getAddress(),
                        recordDto.getPetServiceDto().getName()));

        sendMessage.setReplyMarkup(replyKeyboardService.getConfirmRecordKeyboard());

        return sendMessage;
    }

}
