import org.glassfish.grizzly.utils.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ExampleTest {
    private static String Condition;
    private static String Answer;
    private static ArrayList<Pair<String, String>> Examples;
    private static Example ExamplesClass;

    @BeforeAll
    private static void setUp() {
        Condition = "80+3";
        Answer = "83";
        Examples = new ArrayList<Pair<String, String>>(Arrays.asList(new Pair<String, String>(Condition, Answer)));
        ExamplesClass = new Example(Examples);
    }

    @Test
    void GetIssueTest(){
        assertEquals(Condition, ExamplesClass.GetExample());
    }

    @Test
    void compareResult() {
        assertEquals(false, ExamplesClass.CompareResult(Condition, "3").getFirst());
        assertTrue(ExamplesClass.CompareResult(Condition, "83").getFirst());
    }
}