package Tests;

import main.java.Clue;
import main.java.GetClue;
import main.java.Tasks;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CluesTest {
    static HashMap<String, String> userTask = Clue.GetClues();
    static String str = "Упс! Это достаточно легко, для этого подсказки нет(";
    static Object[] issue = userTask.keySet().toArray();
    static GetClue clue = new GetClue();
    @Test
    void getClue() {
        assertEquals(str, clue.getClue(issue[0].toString()));
        var pr  = clue.getClue(issue[1].toString());
        assertEquals("Это вопрос на эрудицию, можешь найти ответ в интернете", clue.getClue(issue[1].toString()));
    }
}
