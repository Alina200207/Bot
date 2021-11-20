package Tests;

import main.java.Tasks;
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
    private static final HashMap<String, String> Issues = new HashMap<String, String>();
    private static WorkingOnTask examplesClass;
    private static WorkingOnTask issueClass;
    private static final ArrayList<String> UsedTasks = new ArrayList<String>();
    static HashMap<String, String> userTask = Tasks.GetIssues();
    static String issue = userTask.keySet().toArray()[0].toString();

    @BeforeAll
    private static void setUp() {
        Condition = "80+3";
        String answer = "83";
        String anotherCondition = "5*10";
        String anotherAnswer = "50";
        Examples.put(Condition, answer);
        Examples.put(anotherCondition, anotherAnswer);
        Issues.put(userTask.get(issue), issue);
        UsedTasks.add(anotherCondition);
        examplesClass = new WorkingOnTask(Examples);
        issueClass = new WorkingOnTask(Issues);
    }

    @Test
    void GetIssueTest(){
        assertEquals(Condition, examplesClass.getTask(UsedTasks));
    }

    @Test
    void compareResult() {
        assertEquals(false, examplesClass.compareResult(Condition, "3").correctness);
        assertEquals(true, issueClass.compareResult(userTask.get(issue), issue).correctness);
        assertTrue(examplesClass.compareResult(Condition, "83").correctness);
    }
}