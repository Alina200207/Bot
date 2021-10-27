package Tests;

import Main.Bot;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import static org.junit.jupiter.api.Assertions.*;

class BotTest {
    private Bot bot = new Bot();

    @Test
    void getBotToken() {
        assertEquals(bot.getBotToken(), "1983849094:AAEDDaWbNZTadHWRfyVrr6nMups8zRk1CNI");
        assertNotNull(bot.getBotToken());
    }

    @Test
    void getBotUsername() {
        assertNotNull(bot.getBotUsername());
        assertEquals(bot.getBotUsername(), "@Pump_up_your_brain_bot");
    }
    Update update = new Update();
    Message message = new Message();
    @Test
    void onUpdateReceived() {
        message.setText("/help");
        update.setMessage(message);
        update.setUpdateId(1);
        //assertNotNull(bot.onUpdateReceived(update));
    }

}