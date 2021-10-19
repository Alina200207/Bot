import org.glassfish.grizzly.utils.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(Condition, IssueClass.GetCondition(new ArrayList()));
    }

    @Test
    void CompareResultTest(){
        assertEquals(false, IssueClass.CompareResult(Condition, "No!").getFirst());
        assertTrue(IssueClass.CompareResult(Condition, "Sorry, I can't").getFirst());
    }
}
