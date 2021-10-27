package Tests;

import Main.StartCommand;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static org.junit.jupiter.api.Assertions.*;

class StartCommandTest {
    String text = "Привет! Я бот, который поможет прокачать твой мозг. \n \nВ зависимости от твоего " +
            "выбора я вышлю тебе либо загадки, либо последовательности, либо примеры." +
            "\n \nТебе предстоит выполнить соответствующие задания за ограниченное время." +
            " Чтобы ознакомиться со списком команд нажми на /help";
    @Test
    void start() {
        SendMessage message = new SendMessage();
        assertEquals(StartCommand.start(), text);
    }
}