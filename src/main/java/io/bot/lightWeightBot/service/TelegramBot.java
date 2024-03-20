package io.bot.lightWeightBot.service;

import io.bot.lightWeightBot.scenes.Scenes;
import io.bot.lightWeightBot.config.BotConfig;
import io.bot.lightWeightBot.database.DataBaseHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVoice;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String answer;
            String messageText = update.getMessage().getText();
            long chatID = update.getMessage().getChatId();
            System.out.println(messageText);
            DataBaseHandler sendler = new DataBaseHandler();
            sendler.addUser(update.getMessage().getChat().getFirstName() + " " + update.getMessage().getChat().getLastName(), update.getMessage().getChatId().toString());
            Scenes scenes = new Scenes(messageText, update.getMessage().getChat().getFirstName());
            if (messageText.startsWith("/")) {
                switch (messageText) {
                    case "/start":
                        scenes.setAction(Scenes.Actions.START);
                        System.out.println(String.valueOf(chatID));
                        sendMessage(chatID, scenes.getAnswer());
                        sendVoiceMessage(chatID, "D:\\JavaProjects\\lightWeightBot\\lightWeightBot\\src\\main\\resources\\audio.mp3");
                        break;
                    default:
                        sendMessage(chatID, "Сука, сидит ломает программу, иди занимайся");
                        break;
                }
            } else {
                scenes.setAction(Scenes.Actions.WARMUP);
                sendMessage(chatID, scenes.getAnswer());
                scenes.setAction(Scenes.Actions.TRAINING);
                sendMessage(chatID, scenes.getAnswer());
            }
        }
    }

    public void sendMessage(long chatID, String answerText) {
        if (answerText != null) {
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatID));
            message.setText(answerText);
            try {
                execute(message);
            } catch (TelegramApiException error) {
                throw new RuntimeException(error);
            }
        }
    }

    public void sendVoiceMessage(Long chatId, String voiceFilePath) {
        SendVoice sendVoice = new SendVoice();
        sendVoice.setChatId(chatId.toString());

        InputFile voice = new InputFile(new File(voiceFilePath));
        sendVoice.setVoice(voice);

        try {
            execute(sendVoice);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}