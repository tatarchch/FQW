package com.example.FQW.telegram.handler;

import com.example.FQW.dto.ClientDto;
import com.example.FQW.dto.RecordDto;
import com.example.FQW.telegram.bot.BotService;
import com.example.FQW.telegram.constant.BotMessageEnum;
import com.example.FQW.telegram.constant.ButtonNameEnum;
import com.example.FQW.telegram.keyboard.InlineKeyboardService;
import com.example.FQW.telegram.keyboard.ReplyKeyboardService;
import com.example.FQW.telegram.utils.ConverterDataToKeyboardUtils;
import com.example.FQW.telegram.utils.LogUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MessageHandler {

    BotService botService;

    InlineKeyboardService inlineKeyboardService;
    ReplyKeyboardService replyKeyboardService;

    public SendMessage answerMessage(Message message, Map<String, RecordDto> recordMap) {
        SendMessage sendMessage = new SendMessage();
        ButtonNameEnum command = ConverterDataToKeyboardUtils.getByValue(message.getText());

        switch (command) {
            case START -> sendMessage = startCommand(message, recordMap);
            case GET_PLACE -> sendMessage = placeCommand(message);
            case GET_MASTER -> sendMessage = masterCommand(message, recordMap);
            case GET_SERVICE_BY_MASTER -> sendMessage = serviceWithMasterCommand(message, recordMap);
            case GET_SERVICE -> sendMessage = serviceCommand(message);
            case GET_MASTER_BY_SERVICE -> sendMessage = masterWithServiceCommand(message, recordMap);
            case GET_DATE -> sendMessage = dateCommand(message, recordMap);
            case APPLY -> sendMessage = confirmCommand(message, recordMap);
            case CANCEL -> sendMessage = cancelCommand(message, recordMap);
            default -> {
                sendMessage.setChatId(String.valueOf(message.getChatId()));
                sendMessage.setText(BotMessageEnum.EXCEPTION_WHAT_THE_FUCK.getMessage());
                LogUtils.getErrorLogForReplyKeyboard();
            }
        }
        return sendMessage;
    }

    private SendMessage startCommand(Message message, Map<String, RecordDto> recordMap) {
        RecordDto recordDto = new RecordDto();
        String answer = BotMessageEnum.START.getMessage();
        String userName = message.getChat().getUserName();

        ClientDto clientDto = botService.getClientService().botLogin(userName);
        recordDto.setClientDto(clientDto);
        recordMap.put(userName, recordDto);

        LogUtils.getLoginLog(userName);

        SendMessage sendMessage = new SendMessage(String.valueOf(message.getChatId()), String.format(answer, clientDto.getLogin()));
        sendMessage.setReplyMarkup(replyKeyboardService.getAfterStartMenuKeyboard());

        return sendMessage;
    }

    private SendMessage placeCommand(Message message) {
        String answer = BotMessageEnum.PLACE_START.getMessage();
        SendMessage sendMessage = new SendMessage(String.valueOf(message.getChatId()), answer);

        sendMessage.setReplyMarkup(inlineKeyboardService.getInlineMessageButtonsForPlace());

        return sendMessage;
    }

    private SendMessage masterCommand(Message message, Map<String, RecordDto> recordMap) {
        String answer = BotMessageEnum.MASTER_START.getMessage();
        SendMessage sendMessage = new SendMessage(String.valueOf(message.getChatId()), answer);

        Long placeId = recordMap.get(message.getChat().getUserName()).getPlaceDto().getId();

        sendMessage.setReplyMarkup(inlineKeyboardService.getInlineMessageButtonsForMaster(placeId));
        return sendMessage;
    }

    private SendMessage serviceCommand(Message message) {
        String answer = BotMessageEnum.SERVICE_START.getMessage();
        SendMessage sendMessage = new SendMessage(String.valueOf(message.getChatId()), answer);

        sendMessage.setReplyMarkup(inlineKeyboardService.getInlineMessageButtonsForServices());

        return sendMessage;
    }

    private SendMessage serviceWithMasterCommand(Message message, Map<String, RecordDto> recordMap) {
        String answer = BotMessageEnum.SERVICE_START.getMessage();
        SendMessage sendMessage = new SendMessage(String.valueOf(message.getChatId()), answer);

        Long masterId = recordMap.get(message.getChat().getUserName()).getMasterDto().getId();

        sendMessage.setReplyMarkup(inlineKeyboardService.getInlineMessageButtonsForServiceWithMaster(masterId));

        return sendMessage;
    }

    private SendMessage masterWithServiceCommand(Message message, Map<String, RecordDto> recordMap) {
        String answer = BotMessageEnum.MASTER_START.getMessage();
        SendMessage sendMessage = new SendMessage(String.valueOf(message.getChatId()), answer);

        Long placeId = recordMap.get(message.getChat().getUserName()).getPlaceDto().getId();
        Long serviceId = recordMap.get(message.getChat().getUserName()).getServiceDto().getId();

        sendMessage.setReplyMarkup(inlineKeyboardService.getInlineMessageButtonsForMasterWithService(placeId, serviceId));

        return sendMessage;
    }

    private SendMessage dateCommand(Message message, Map<String, RecordDto> recordMap) {
        String answer = BotMessageEnum.DATE_START.getMessage();
        SendMessage sendMessage = new SendMessage(String.valueOf(message.getChatId()), answer);

        Long masterId = recordMap.get(message.getChat().getUserName()).getMasterDto().getId();

        sendMessage.setReplyMarkup(inlineKeyboardService.getInlineMessageButtonsForDate(masterId));

        return sendMessage;
    }

    private SendMessage confirmCommand(Message message, Map<String, RecordDto> recordMap) {
        String answer = BotMessageEnum.CONFIRM.getMessage();
        RecordDto recordDto = botService.getRecordService().confirm(recordMap.get(message.getChat().getUserName()));

        LogUtils.getConfirmLog(message, recordDto);

        SendMessage sendMessage = new SendMessage(String.valueOf(message.getChatId()), String.format(answer, recordDto));
        sendMessage.setReplyMarkup(replyKeyboardService.getFreeKeyboard());

        return sendMessage;
    }

    private SendMessage cancelCommand(Message message, Map<String, RecordDto> recordMap) {
        String userName = message.getChat().getUserName();
        recordMap.remove(userName);
        RecordDto recordDto = new RecordDto();
        String answer = BotMessageEnum.CANCEL.getMessage();

        LogUtils.getCancelLog(message, recordDto);

        SendMessage sendMessage = new SendMessage(String.valueOf(message.getChatId()), String.format(answer, recordDto));
        sendMessage.setReplyMarkup(replyKeyboardService.getStartMenuKeyboard());

        return sendMessage;
    }

}