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
        if(update.getMessage()!=null && update.getMessage().getText().equals("/help"))
        {
            Long chat_Id = update.getMessage().getChatId();
            var help = new Help();
            try{
                execute(new SendMessage(chat_Id.toString(), help.giveHelp()));

            }
            catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
