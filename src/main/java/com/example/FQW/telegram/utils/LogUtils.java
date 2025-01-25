package com.example.FQW.telegram.utils;

import com.example.FQW.dto.*;
import com.example.FQW.telegram.constant.BotMessageEnum;
import com.example.FQW.telegram.constant.LogMessageEnum;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@UtilityClass
public class LogUtils {

    public void getLoginLog(String userName) {
        log.info(String.format(LogMessageEnum.LOGIN.getText(), userName));
    }

    public void getPlaceLog(String userName, PlaceDto placeDto) {
        log.info(String.format(LogMessageEnum.PICK_PLACE.getText(),
                userName,
                placeDto.getName()));
    }

    public void getMasterLog(String userName, MasterDto masterDto) {
        log.info(String.format(LogMessageEnum.PICK_MASTER.getText(),
                userName,
                masterDto.getName()));
    }

    public void getServiceLog(String userName, ServiceDto serviceDto) {
        log.info(String.format(LogMessageEnum.PICK_SERVICE.getText(),
                userName,
                serviceDto.getName()));
    }

    public void getDateLog(String userName, CalendarDto calendarDto) {
        log.info(String.format(LogMessageEnum.PICK_DATE.getText(),
                userName,
                calendarDto.getDate().toString()));
    }

    public void getTimeLog(String userName, RecordDto recordDto) {
        log.info(String.format(LogMessageEnum.PICK_TIME.getText(),
                userName,
                recordDto.getTiming()));
    }

    public void getConfirmLog(Message message, RecordDto recordDto) {
        log.info(String.format(LogMessageEnum.CONFIRM.getText(),
                message.getChat().getUserName(),
                recordDto));
    }

    public void getCancelLog(Message message, RecordDto recordDto) {
        log.info(String.format(LogMessageEnum.CANCEL.getText(),
                message.getChat().getUserName(),
                recordDto));
    }

    public void getErrorLogForReplyKeyboard() {
        log.error(String.format(LogMessageEnum.REPLY_KEYBOARD.getText(),
                BotMessageEnum.EXCEPTION_WHAT_THE_FUCK.getMessage()));
    }

    public void getErrorLogForInlineKeyboard() {
        log.error(String.format(LogMessageEnum.INLINE_KEYBOARD.getText(),
                BotMessageEnum.EXCEPTION_BAD_BUTTON_NAME_MESSAGE.getMessage()));
    }

}
