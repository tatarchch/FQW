package com.example.FQW.telegram;

import com.example.FQW.dto.RecordDto;
import com.example.FQW.telegram.constant.BotMessageEnum;
import com.example.FQW.telegram.handler.CallbackQueryHandler;
import com.example.FQW.telegram.handler.MessageHandler;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

@Component
@Setter
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final CallbackQueryHandler callbackQueryHandler;
    private final MessageHandler messageHandler;
    private HashMap<String, RecordDto> recordMap = new HashMap<>();

    public TelegramBot(@Value("${bot.token}") String botToken,
                       CallbackQueryHandler callbackQueryHandler,
                       MessageHandler messageHandler) {
        super(botToken);
        this.callbackQueryHandler = callbackQueryHandler;
        this.messageHandler = messageHandler;
    }

    @Override
    public String getBotUsername() {
        return "fqwTest_bot";
    }

    @Override
    public void onUpdateReceived(Update update) {
        //либо возврашает один из ответов хендла(с инлайн или реплай клавы), либо отвечает на ошибки
        try {
            SendMessage sendMessage = this.handleUpdate(update);
            execute(sendMessage);
            //execute(this.handleUpdate(update));
        } catch (TelegramApiException e) {
            new SendMessage(update.getMessage().getChatId().toString(), BotMessageEnum.EXCEPTION_WHAT_THE_FUCK.getMessage());
            log.warn("Предупреждение: {}", e.getMessage());
        }
    }

    private SendMessage handleUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            return callbackQueryHandler.answerCallbackQuery(update.getCallbackQuery(), this.recordMap);
        } else {
            if (update.getMessage() != null) {
                return messageHandler.answerMessage(update.getMessage(), this.recordMap);
            }
        }
        return null;
    }

}
