import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.bots.DefaultBotOptions;

public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotToken() {
        return "1983849094:AAEDDaWbNZTadHWRfyVrr6nMups8zRk1CNI";
    }

    @Override
    public String getBotUsername() {
        return "@Pump_up_your_brain_bot";
    }

    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();
        Long chat_Id = update.getMessage().getChatId();
        String inputText = update.getMessage().getText();
        SendMessage message = null;
        message = new SendMessage();
        message.setChatId(String.valueOf(chat_Id));
        switch (inputText){
            case "/start":
                message.setText(StartCommand.start());
                break;
            case "/help":
                var help = new Help();
                message = new SendMessage(chat_Id.toString(), help.giveHelp());
                break;
            case "/examples":
                message.setText(StartCommand.start());
                break;
            case "/sequences":
                message.setText(StartCommand.start());
                break;
            case "/issue":
                message.setText(StartCommand.start());
                break;
            case "/level":
                message.setText(StartCommand.start());
                break;
            default:
                message.setText(StartCommand.start());
        }
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
