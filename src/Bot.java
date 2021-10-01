import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
        if(update.getMessage()!=null && update.getMessage().hasText())
        {
            String message = update.getMessage().getText();
            Long chat_Id = update.getMessage().getChatId();
            switch (message)
            {
                case "/help":
                    try {
                        execute(Help_Command.giveHelp(chat_Id.toString()));
                    }
                    catch (TelegramApiException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case "/start":
                    break;
                case "/examples":
                    break;
                case "/Issue":
                    break;
                case "/Sequences":
                    break;
                case "/Level":
                    break;
                default:
            }

        }
    }
}
