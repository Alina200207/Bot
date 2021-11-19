package Tests;

import Main.LevelCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LevelCommandTest {
    private final LevelCommand levelCommand = new LevelCommand();

    @Test
    void giveAnswer() {
        int playerAnswer = (int)(Math.random() * 26 + 4); // диапазон от 4 до 30
        String answerFalse = "Ты ввел неправильную цифру. Попробуй снова /level";
        assertEquals(answerFalse, levelCommand.getAnswer(Integer.toString(playerAnswer)));
        playerAnswer = (int)(Math.random() * 4); // диапазон от 0 до 3
        var answerTrue = "Ваш текущий уровень:" + playerAnswer + "\nВыбирай команду:\n" + "/issue \n" + "/examples \n" +
                "/sequences";
        assertEquals(answerTrue, levelCommand.getAnswer(Integer.toString(playerAnswer)));
    }
}