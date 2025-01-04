package com.example.FQW.telegram.bot;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Setter
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private static final String START = "/start";
    private static final String LOGIN = "/login";
    private static final String REGISTRATION = "/registration";
    private static final String PLACE = "/place";
    private static final String MASTER = "/master";
    private static final String SERVICE = "/service";

    public TelegramBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public String getBotUsername() {
        return "fqwTest_bot";
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();

            //для каждого обраточика класс
            switch (messageText) {
                case START -> {
                    startCommand(chatId, update.getMessage().getChat().getUserName());
                }
            }
        }
    }

    private void startCommand(Long chatId, String userName) {
        String text = "Добро пожаловать, %s!" +
                "Здесь вы можете записаться " +
                "в салон по уходу за домашними животными";

        String formattedText = String.format(text, userName);
        sendMessage(chatId, formattedText);
        log.info("Ответ");

    }

    private void sendMessage(Long chatId, String text) {
        String chatIdStr = String.valueOf(chatId);
        SendMessage sendMessage = new SendMessage(chatIdStr, text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Ошибка, отправки сообщения", e);
        }
    }


}
