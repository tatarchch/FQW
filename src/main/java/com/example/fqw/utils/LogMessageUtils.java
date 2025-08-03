package com.example.fqw.utils;

import com.example.fqw.dto.*;
import com.example.fqw.enums.BotMessageEnum;
import com.example.fqw.enums.LogMessageEnum;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Message;

@UtilityClass
public class LogMessageUtils {

    public String getLoginLogMessage(String userName) {
        return String.format(LogMessageEnum.LOGIN.getText(), userName);
    }

    public String getPlaceLogMessage(String userName, PlaceDto placeDto) {
        return String.format(LogMessageEnum.PICK_PLACE.getText(),
                userName,
                placeDto.getName());
    }

    public String getMasterLogMessage(String userName, MasterDto masterDto) {
        return String.format(LogMessageEnum.PICK_MASTER.getText(),
                userName,
                masterDto.getName());
    }

    public String getServiceLogMessage(String userName, PetServiceDto petServiceDto) {
        return String.format(LogMessageEnum.PICK_SERVICE.getText(),
                userName,
                petServiceDto.getName());
    }

    public String getDateLogMessage(String userName, CalendarDto calendarDto) {
        return String.format(LogMessageEnum.PICK_DATE.getText(),
                userName,
                calendarDto.getDate().toString());
    }

    public String getTimeLogMessage(String userName, RecordDto recordDto) {
        return String.format(LogMessageEnum.PICK_TIME.getText(),
                userName,
                recordDto.getTiming());
    }

    public String getConfirmLogMessage(Message message, RecordDto recordDto) {
        return String.format(LogMessageEnum.CONFIRM.getText(),
                message.getChat().getUserName(),
                recordDto.getPetServiceDto().getId(),
                recordDto.getMasterDto().getId(),
                recordDto.getDate(),
                recordDto.getTiming());
    }

    public String getCancelLogMessage(Message message, String userName) {
        return String.format(LogMessageEnum.CANCEL.getText(),
                message.getChat().getUserName(),
                userName);
    }


    public String getErrorLogMessageForReplyKeyboard() {
        return String.format(LogMessageEnum.REPLY_KEYBOARD.getText(),
                BotMessageEnum.EXCEPTION_WHAT_THE_FUCK.getMessage());
    }


    public String getErrorLogMessageForInlineKeyboard() {
        return String.format(LogMessageEnum.INLINE_KEYBOARD.getText(),
                BotMessageEnum.EXCEPTION_BAD_BUTTON_NAME_MESSAGE.getMessage());
    }

}
