package Tests;

import Main.WorkingOnTask;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkingOnTaskTest {
    private static String Condition;
    private static String Answer;
    private static HashMap<String, String> Examples = new HashMap<String, String>();
    private static WorkingOnTask examplesClass;
    private static ArrayList<String> UsedTasks = new ArrayList<String>();

    @BeforeAll
    private static void setUp() {
        Condition = "80+3";
        Answer = "83";
        Examples.put(Condition, Answer);
        examplesClass = new WorkingOnTask(Examples);
    }

    @Test
    void GetIssueTest(){
        assertEquals(Condition, examplesClass.getTask(UsedTasks));
    }

    @Test
    void compareResult() {
        assertEquals(false, examplesClass.compareResult(Condition, "3").getFirst());
        assertTrue(examplesClass.compareResult(Condition, "83").getFirst());
    }
}