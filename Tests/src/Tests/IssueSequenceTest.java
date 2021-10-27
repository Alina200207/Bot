package Tests;

import Main.IssueSequence;
import org.glassfish.grizzly.utils.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IssueSequenceTest {
    private static String Condition;
    private static String Answer;
    private static ArrayList<Pair<String, String>> Issues;
    private static IssueSequence IssueClass;

    @BeforeAll
    private static void setUp() {
        Condition = "Can you save me from Python?";
        Answer = "Sorry, I can't";
        Issues = new ArrayList<Pair<String, String>>(Arrays.asList(new Pair<String, String>(Condition, Answer)));
        IssueClass = new IssueSequence(Issues);
    }

    @Test
    void GetIssueTest(){
        assertEquals(Condition, IssueClass.getTask(new ArrayList()));
    }

    @Test
    void CompareResultTest(){
        assertEquals(false, IssueClass.compareResult(Condition, "No!").getFirst());
        assertTrue(IssueClass.compareResult(Condition, "Sorry, I can't").getFirst());
    }
}
