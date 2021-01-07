package com.github.jfr0st.application;


import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.WebhookBot;


public class Application  extends TelegramLongPollingBot {

    final static String START_CONST = "/start";
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new Application());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void replay(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()){
                case START_CONST:
                    replay(message, "я гадал, что сломается раньше, твой дух или твое тело");
                    break;
                default:
            }
        }
    }


    public String getBotUsername() {
        return "project_pet_bot";
    }

    public String getBotToken() {
        return "1516907897:AAGV--FRzJ9n8qUjcBHTzJF70sG4qReYP0U";
    }
}
