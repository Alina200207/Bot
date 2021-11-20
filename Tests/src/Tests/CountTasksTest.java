package Tests;

import main.java.CountTasks;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountTasksTest {
    static CountTasks countTasks;
    @BeforeAll
    static void setUp(){
        countTasks = new CountTasks(2, 0, 1);
    }
    @Test
    void initializationPercentStatistic() {
        assertEquals(2, countTasks.countExamples);
        assertEquals(0, countTasks.countSequences);
        assertEquals(1, countTasks.countIssues);
        assertEquals(3, countTasks.countAllTasks);
    }
}
