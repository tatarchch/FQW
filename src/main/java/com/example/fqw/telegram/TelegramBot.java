package com.example.fqw.telegram;

import com.example.fqw.dto.RecordDto;
import com.example.fqw.exception.TelegramUserNotFound;
import com.example.fqw.enums.BotMessageEnum;
import com.example.fqw.telegram.handler.CallbackQueryHandler;
import com.example.fqw.telegram.handler.MessageHandler;
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
        try {
            execute(this.handleUpdate(update));
        } catch (TelegramApiException e) {
            new SendMessage(update.getMessage().getChatId().toString(), BotMessageEnum.EXCEPTION_WHAT_THE_FUCK.getMessage());
            log.warn("Предупреждение: {}", e.getMessage());
        }
    }

    private SendMessage handleUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            return callbackQueryHandler.answerCallbackQuery(update.getCallbackQuery(), this.recordMap);
        } else if (update.getMessage() != null) {
            return messageHandler.answerMessage(update.getMessage(), this.recordMap);
        } else {
            return new SendMessage(String.valueOf(update.getMessage().getChatId()), BotMessageEnum.EXCEPTION_WHAT_THE_FUCK.getMessage());
        }
    }

    public void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new TelegramUserNotFound();
        }
    }

}
