package com.maystrovyy.bot.processors;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public interface ClassBotProcessor {

    void processUpdate(Update update);
    void processCallback(Update update);

    default SendMessage createMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        return message;
    }

    default SendMessage createMessageWithKeyboard(Long chatId, String text, ReplyKeyboardMarkup keyboardMarkup) {
        SendMessage message = createMessage(chatId, text);
        message.setReplyMarkup(keyboardMarkup);
        return message;
    }

    default SendMessage createMessageForDailyPeriods(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Детальніше");
        button.setCallbackData("%details%");

        ArrayList<InlineKeyboardButton> row = new ArrayList<>();
        row.add(button);
        ArrayList<List<InlineKeyboardButton>> rows = new ArrayList<>();
        rows.add(row);
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.setKeyboard(rows);

        message.setReplyMarkup(keyboard);
        return message;
    }

    default EditMessageText editMessageForDailyPeriods(CallbackQuery callbackQuery, String text) {
        EditMessageText message = new EditMessageText();
        message.setChatId(callbackQuery.getMessage().getChatId());
        message.setText(text);
        message.setMessageId(callbackQuery.getMessage().getMessageId());

        return message;
    }

    default SendMessage createMessageWithButton(Long chatId, String text) {
        SendMessage message = createMessage(chatId, text);

        ArrayList<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton firstButton = new InlineKeyboardButton();
        firstButton.setText("Студент");
        firstButton.setCallbackData("%iamstudent%");
        row.add(firstButton);

        InlineKeyboardButton secondButton = new InlineKeyboardButton();
        secondButton.setText("Викладач");
        secondButton.setCallbackData("%iamteacher%");
        row.add(secondButton);

        ArrayList<List<InlineKeyboardButton>> rows = new ArrayList<>();
        rows.add(row);

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.setKeyboard(rows);

        message.setReplyMarkup(keyboard);
        return message;
    }

    default ReplyKeyboardMarkup menuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardButton button1 = new KeyboardButton("Розклад");
        KeyboardButton button2 = new KeyboardButton("Викладачі");
        keyboardFirstRow.add(button1);
        keyboardFirstRow.add(button2);

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        KeyboardButton button3 = new KeyboardButton("Екстра");
        KeyboardButton button4 = new KeyboardButton("Допомога");
        keyboardSecondRow.add(button3);
        keyboardSecondRow.add(button4);

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

    default ReplyKeyboardMarkup extraMenuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardButton button1 = new KeyboardButton("Змінити групу");
        KeyboardButton button2 = new KeyboardButton("Редагувати розклад");

        keyboardFirstRow.add(button1);
        keyboardFirstRow.add(button2);

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        KeyboardButton button3 = new KeyboardButton("Нагадування");
        KeyboardButton button4 = new KeyboardButton("Контакти");
        keyboardSecondRow.add(button3);
        keyboardSecondRow.add(button4);

        KeyboardRow keyboardThirdRow = new KeyboardRow();
        KeyboardButton button5 = new KeyboardButton("Назад");
        keyboardThirdRow.add(button5);

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }


    default ReplyKeyboardMarkup scheduleMenuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardButton button1 = new KeyboardButton("Сьогодні");
        KeyboardButton button2 = new KeyboardButton("Завтра");
        KeyboardButton button3 = new KeyboardButton("Тиждень");
        keyboardFirstRow.add(button1);
        keyboardFirstRow.add(button2);
        keyboardFirstRow.add(button3);

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        KeyboardButton button4 = new KeyboardButton("Назад");
        keyboardSecondRow.add(button4);

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

}
