package Tests;

import Main.CountTasks;
import Main.Statistic;
import Main.Type;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticTest {
    private static HashMap<Type.TypeTask, ArrayList<String>> usedTasks = new HashMap<>();
    private Statistic statistic = new Statistic();

    @BeforeAll
    private static void setUp() {
        ArrayList<String> issues = new ArrayList<>();
        issues.add("Висит груша, нельзя скушать");
        ArrayList<String> sequences = new ArrayList<>();
        sequences.add("45 54 45 54");
        sequences.add("76 98 12");
        ArrayList<String> examples = new ArrayList<>();
        examples.add("23+89");
        usedTasks.put(Type.TypeTask.Issue, issues);
        usedTasks.put(Type.TypeTask.Sequence, sequences);
        usedTasks.put(Type.TypeTask.Example, examples);
    }

    @Test
    void getStatisticTest(){
        CountTasks counterTasks =  new CountTasks(1, 2, 1);
        var statisticCount = statistic.getCountTasks(usedTasks);
        assertEquals(counterTasks.countAllTasks, statisticCount.countAllTasks);
        assertEquals(counterTasks.countExamples, statisticCount.countExamples);
        assertEquals(counterTasks.countIssues, statisticCount.countIssues);
        assertEquals(counterTasks.countSequences, statisticCount.countSequences);
    }
}

