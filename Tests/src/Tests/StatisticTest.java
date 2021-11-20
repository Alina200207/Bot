package Tests;

import main.java.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticTest {
    private static final HashMap<Type.TypeTask, ArrayList<String>> usedTasks = new HashMap<>();
    static Map<String, UserData> usersData = new HashMap<>();
    private final Statistic statistic = new Statistic();
    static CountTasks counterTasks =  new CountTasks(1, 2, 1);
    static CountTasks anotherCounterTasks = new CountTasks(3, 4, 6);
    static UserData firstUser = new UserData("11111");
    static UserData secondUser = new UserData("22222");
    static UserData thirdUser = new UserData("33333");

    @BeforeAll
    private static void setUp() {
        firstUser.setLastStatistic(counterTasks);
        secondUser.setLastStatistic(anotherCounterTasks);
        thirdUser.setLastStatistic(new CountTasks(3, 2, 4));
        usersData.put("11111", firstUser);
        usersData.put("22222", secondUser);
        usersData.put("33333", thirdUser);
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
        var statisticCount = statistic.getCountTasks(usedTasks);
        assertEquals(4, statisticCount.countAllTasks);
        assertEquals(counterTasks.countExamples, statisticCount.countExamples);
        assertEquals(1, statisticCount.countIssues);
        assertEquals(counterTasks.countSequences, statisticCount.countSequences);
    }

    @Test
    void getDifferenceBetweenStatisticTest(){
        var differenceCounterTasks = statistic.getCountTasks(counterTasks, anotherCounterTasks);
        assertEquals(9, differenceCounterTasks.countAllTasks);
        assertEquals(2, differenceCounterTasks.countExamples);
        assertEquals(5, differenceCounterTasks.countIssues);
        assertEquals(2, differenceCounterTasks.countSequences);
    }

    @Test
    void getComparativeStatisticTest(){
        var percentStatistic = new PercentStatistic(2.0/3*100, 0.0, 3);
        var comparativeSecond = statistic.getComparativeStatistic(usersData, secondUser);
        assertEquals(percentStatistic.percentFewer, comparativeSecond.percentFewer);
        assertEquals(percentStatistic.percentSame, comparativeSecond.percentSame);
        assertEquals(percentStatistic.countUsers, comparativeSecond.countUsers);
    }
}

