package com.example.fqw.telegram.handler;

import com.example.fqw.dto.ClientDto;
import com.example.fqw.dto.RecordDto;
import com.example.fqw.enums.BotMessageEnum;
import com.example.fqw.enums.ButtonNameEnum;
import com.example.fqw.services.ClientService;
import com.example.fqw.services.RecordService;
import com.example.fqw.telegram.keyboard.InlineKeyboardService;
import com.example.fqw.telegram.keyboard.ReplyKeyboardService;
import com.example.fqw.utils.LogUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageHandler {

    ClientService clientService;
    RecordService recordService;

    InlineKeyboardService inlineKeyboardService;
    ReplyKeyboardService replyKeyboardService;

    public SendMessage answerMessage(Message message, Map<String, RecordDto> recordMap) {
        SendMessage sendMessage = new SendMessage();
        ButtonNameEnum command = ButtonNameEnum.getByValue(message.getText());


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
                sendMessage.setText(BotMessageEnum.EXCEPTION_BAD_BUTTON_NAME_MESSAGE.getMessage());
                LogUtils.getErrorLogForReplyKeyboard();
            }
        }
        return sendMessage;
    }

    private SendMessage startCommand(Message message, Map<String, RecordDto> recordMap) {
        RecordDto recordDto = new RecordDto();
        String answer = BotMessageEnum.START.getMessage();
        String userName = message.getChat().getUserName();
        String chatId = String.valueOf(message.getChatId());

        ClientDto clientDto = clientService.botLogin(chatId, userName);
        clientDto.setChatId(chatId);
        recordDto.setClientDto(clientDto);
        recordMap.put(userName, recordDto);

        LogUtils.loginLog(userName);

        SendMessage sendMessage = new SendMessage(chatId, String.format(answer, userName));
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
        Long serviceId = recordMap.get(message.getChat().getUserName()).getPetServiceDto().getId();

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
        String userName = message.getChat().getUserName();
        String answer = BotMessageEnum.CONFIRM.getMessage();
        RecordDto recordDto = recordService.confirm(recordMap.get(userName));

        recordMap.remove(userName);

        LogUtils.getConfirmLog(message, recordDto);

        SendMessage sendMessage = new SendMessage(String.valueOf(message.getChatId()), String.format(answer, recordDto));
        sendMessage.setReplyMarkup(replyKeyboardService.getStartMenuKeyboard());

        return sendMessage;
    }

    private SendMessage cancelCommand(Message message, Map<String, RecordDto> recordMap) {
        String userName = message.getChat().getUserName();
        String answer = BotMessageEnum.CANCEL.getMessage();
        recordMap.remove(userName);

        LogUtils.getCancelLog(message, userName);

        SendMessage sendMessage = new SendMessage(String.valueOf(message.getChatId()), answer);
        sendMessage.setReplyMarkup(replyKeyboardService.getStartMenuKeyboard());

        return sendMessage;
    }

}