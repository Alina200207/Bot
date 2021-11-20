package Tests;

import main.java.Condition;
import main.java.Tasks;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static main.java.Bot.State.Example;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConditionTest {
    static Condition condition;
    static Set<String> userTask = Tasks.GetExamplesLevel1().keySet();
    static String example = userTask.toArray()[0].toString();

    @BeforeAll
    static void setUp(){
        condition = new Condition( Example, example);
    }
    @Test
    void initializationPercentStatistic(){
        assertEquals(Example, condition.state);
        assertEquals(example, condition.task);
    }
}
