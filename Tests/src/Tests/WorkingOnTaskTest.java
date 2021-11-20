package Tests;

import main.java.WorkingOnTask;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkingOnTaskTest {
    private static String Condition;
    private static final HashMap<String, String> Examples = new HashMap<String, String>();
    private static WorkingOnTask examplesClass;
    private static final ArrayList<String> UsedTasks = new ArrayList<String>();

    @BeforeAll
    private static void setUp() {
        Condition = "80+3";
        String answer = "83";
        String anotherCondition = "5*10";
        String anotherAnswer = "50";
        Examples.put(Condition, answer);
        Examples.put(anotherCondition, anotherAnswer);
        UsedTasks.add(anotherCondition);
        examplesClass = new WorkingOnTask(Examples);
    }

    @Test
    void GetIssueTest(){
        assertEquals(Condition, examplesClass.getTask(UsedTasks));
    }

    @Test
    void compareResult() {
        assertEquals(false, examplesClass.compareResult(Condition, "3").correctness);
        assertTrue(examplesClass.compareResult(Condition, "83").correctness);
    }
}