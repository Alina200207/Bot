import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
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

    @Test
    void onUpdateReceived() {
        //assertNotNull(bot.onUpdateReceived(bot.update));
    }

}