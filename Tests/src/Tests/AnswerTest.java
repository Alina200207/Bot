package Tests;

import main.java.Answer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerTest {
    static Answer answer;
    @BeforeAll
    static void setUp(){
        answer = new Answer("15", true);
    }
    @Test
    void initializationPercentStatistic(){
        assertEquals("15", answer.answerString);
        assertEquals(true, answer.correctness);
    }
}
