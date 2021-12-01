package Tests;

import main.java.commands.LevelCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LevelCommandTest {

    @Test
    void giveAnswer_ВыдаетСообщениеОбОшибкеИлиМеняетУровень_КогдаНеправильноВвелиЦифруИКогдаВерноСоответственно() {
        int playerAnswer1 = (int)(Math.random() * 26 + 4);
        var playerAnswer2 = (int)(Math.random() * 3 + 1);
        var levelCommand = new LevelCommand();

        var falseResult = levelCommand.getAnswer(Integer.toString(playerAnswer1));
        var trueResult = levelCommand.getAnswer(Integer.toString(playerAnswer2));

        assertEquals("Ты ввел неправильную цифру. Попробуй снова /level", falseResult);
        assertEquals("Ваш текущий уровень:" + playerAnswer2 + "\nВыбирай команду:\n" + "/issue \n" + "/examples \n" +
                "/sequences", trueResult);
    }
}