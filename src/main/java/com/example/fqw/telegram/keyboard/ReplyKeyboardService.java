package com.example.fqw.telegram.keyboard;

import com.example.fqw.enums.ButtonNameEnum;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyKeyboardService {

    public ReplyKeyboardMarkup getStartMenuKeyboard() {
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton(ButtonNameEnum.START.getButtonName()));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);

        return this.createKeyboard(keyboard);
    }

    public ReplyKeyboardMarkup getAfterStartMenuKeyboard() {
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton(ButtonNameEnum.GET_PLACE.getButtonName()));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);

        return this.createKeyboard(keyboard);
    }

    public ReplyKeyboardMarkup getMasterServiceMenuKeyboard() {
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton(ButtonNameEnum.GET_MASTER.getButtonName()));
        row.add(new KeyboardButton(ButtonNameEnum.GET_SERVICE.getButtonName()));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);

        return this.createKeyboard(keyboard);
    }

    public ReplyKeyboardMarkup getServiceWithMasterMenuKeyboard() {
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton(ButtonNameEnum.GET_SERVICE_BY_MASTER.getButtonName()));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);

        return this.createKeyboard(keyboard);
    }

    public ReplyKeyboardMarkup getMasterWithServiceMenuKeyboard() {
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton(ButtonNameEnum.GET_MASTER_BY_SERVICE.getButtonName()));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);

        return this.createKeyboard(keyboard);
    }

    public ReplyKeyboardMarkup getDateMenuKeyboard() {
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton(ButtonNameEnum.GET_DATE.getButtonName()));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);

        return this.createKeyboard(keyboard);
    }

    public ReplyKeyboardMarkup getConfirmRecordKeyboard() {
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton(ButtonNameEnum.APPLY.getButtonName()));
        row.add(new KeyboardButton(ButtonNameEnum.CANCEL.getButtonName()));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);

        return this.createKeyboard(keyboard);
    }

    private ReplyKeyboardMarkup createKeyboard(List<KeyboardRow> keyboard) {
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }

}
