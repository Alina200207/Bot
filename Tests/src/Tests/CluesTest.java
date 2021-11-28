package Tests;

import main.java.Clue;
import main.java.GetClue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CluesTest {

    @Test
    void getClue_ВыдаетПодсказкуИлиСообщениеЧтоЕеНет_КогдаЗагадкаИмеетПодсказкуИКогдаНет() {
        var userTask = Clue.GetClues();
        var issue = userTask.keySet().toArray();
        var clue = new GetClue();

        var result1 = clue.getClue(issue[0].toString());
        var result2 = clue.getClue(issue[1].toString());
        var result3 = clue.getClue(issue[6].toString());

        assertEquals("Упс! Это достаточно легко, для этого подсказки нет(", result1);
        assertEquals("Это вопрос на эрудицию, можешь найти ответ в интернете", result2);
        assertEquals("Это изучают в школе", result3);
    }
}
