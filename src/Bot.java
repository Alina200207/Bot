import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Класс-обработчик поступающих к боту сообщений.
 */
public class Bot extends TelegramLongPollingBot {
    /**
     * Метод, который возвращает токен, выданный нам ботом @BotFather.
     * @return токен
     */
    @Override
    public String getBotToken() {
        return "1983849094:AAEDDaWbNZTadHWRfyVrr6nMups8zRk1CNI";
    }

    /**
     * Метод-обработчик поступающих сообщений.
     * @param update объект, содержащий информацию о входящем сообщении
     */
    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();
        SendMessage sendMessage=new SendMessage();
        if(update.getMessage()!=null && update.getMessage().hasText())
        {
            Long chat_Id = update.getMessage().getChatId();
            try{
                execute(new SendMessage(chat_Id.toString(),"Hello"));
            }
            catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод, который возвращает имя пользователя бота.
     * @return имя пользователя
     */
    @Override
    public String getBotUsername() {
        return "@Pump_up_your_brain_bot";
    }
}
