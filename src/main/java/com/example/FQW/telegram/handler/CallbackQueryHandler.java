package com.example.FQW.telegram.handler;

import com.example.FQW.dto.*;
import com.example.FQW.telegram.bot.BotService;
import com.example.FQW.telegram.constant.BotMessageEnum;
import com.example.FQW.telegram.constant.ButtonNameEnum;
import com.example.FQW.telegram.keyboard.InlineKeyboardService;
import com.example.FQW.telegram.keyboard.ReplyKeyboardService;
import com.example.FQW.telegram.utils.LogUtils;
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
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CallbackQueryHandler {

    BotService botService;

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
            case GET_TIME -> sendMessage = this.getDailyAnswer(chatId, userName, data, recordMap);
            default -> {
                sendMessage.setChatId(String.valueOf(buttonQuery.getMessage().getChatId()));
                sendMessage.setText(BotMessageEnum.EXCEPTION_BAD_BUTTON_NAME_MESSAGE.getMessage());
                LogUtils.getErrorLogForInlineKeyboard();
            }
        }
        return sendMessage;
    }

    private SendMessage getPlaceAnswer(String chatId, String userName, String data, Map<String, RecordDto> recordMap) {
        PlaceDto placeDto = botService.getPlaceService().getPlaceByName(data);

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
        MasterDto masterDto = botService.getMasterService().getMasterByName(data);

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
        ServiceDto serviceDto = botService.getServiceService().getServiceByName(data);

        RecordDto recordDto = recordMap.get(userName);
        recordDto.setServiceDto(serviceDto);
        recordMap.put(data, recordDto);

        LogUtils.getServiceLog(userName, serviceDto);

        SendMessage sendMessage = new SendMessage(chatId, String.format(BotMessageEnum.SERVICE.getMessage(),
                serviceDto.getName(), serviceDto.getCost()));

        sendMessage.setReplyMarkup(replyKeyboardService.getMasterWithServiceMenuKeyboard());

        return sendMessage;
    }

    public SendMessage getServiceWithMasterAnswer(String chatId, String userName, String data, Map<String, RecordDto> recordMap) {
        ServiceDto serviceDto = botService.getServiceService().getServiceByName(data);

        RecordDto recordDto = recordMap.get(userName);
        recordDto.setServiceDto(serviceDto);
        recordMap.put(data, recordDto);
        LogUtils.getServiceLog(userName, serviceDto);

        SendMessage sendMessage = new SendMessage(chatId, String.format(BotMessageEnum.SERVICE.getMessage(),
                serviceDto.getName(), serviceDto.getCost()));

        sendMessage.setReplyMarkup(replyKeyboardService.getDateMenuKeyboard());

        return sendMessage;
    }

    public SendMessage getMasterWithServiceAnswer(String chatId, String userName, String data, Map<String, RecordDto> recordMap) {
        MasterDto masterDto = botService.getMasterService().getMasterByName(data);

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
        CalendarDto calendarDto = botService.getCalendarService()
                .getById(Long.valueOf(data));

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

    public SendMessage getDailyAnswer(String chatId, String userName, String data, Map<String, RecordDto> recordMap) {
        RecordDto recordDto = recordMap.get(userName);
        recordDto.setTiming(data);
        recordMap.put(data, recordDto);

        LogUtils.getTimeLog(userName, recordDto);

        SendMessage sendMessage = new SendMessage(chatId,
                String.format(BotMessageEnum.RECORD.getMessage(),
                        recordDto.getTiming(),
                        recordDto.getDate(),
                        recordDto.getMasterDto().getName(),
                        recordDto.getPlaceDto().getAddress(),
                        recordDto.getServiceDto().getName()));

        sendMessage.setReplyMarkup(replyKeyboardService.getConfirmRecordKeyboard());

        return sendMessage;
    }

}
