package Tests;

import main.java.statistic.Statistic;
import main.java.userData.UserData;
import main.java.structures.CountTasks;
import main.java.structures.Type;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticTest {
    private final Statistic statistic = new Statistic();

    @Test
    void getCountTasks_ВозвращаетКоличествоПравильныхРешенныхЗадачПокаждойКатегорииЗадачИОбщееКоличествоЗадач(){
        HashMap<Type.TypeTask, ArrayList<String>> usedTasks = new HashMap<>();
        ArrayList<String> issues = new ArrayList<>();
        ArrayList<String> sequences = new ArrayList<>();
        ArrayList<String> examples = new ArrayList<>();
        issues.add("Висит груша, нельзя скушать");
        sequences.add("45 54 45 54");
        sequences.add("76 98 12");
        examples.add("23+89");
        usedTasks.put(Type.TypeTask.Issue, issues);
        usedTasks.put(Type.TypeTask.Sequence, sequences);
        usedTasks.put(Type.TypeTask.Example, examples);

        var statisticCount = statistic.getCountTasks(usedTasks);

        assertEquals(4, statisticCount.countAllTasks);
        assertEquals(1, statisticCount.countExamples);
        assertEquals(1, statisticCount.countIssues);
        assertEquals(2, statisticCount.countSequences);
    }

    @Test
    void getCountTasks_ВозвращаетРазницуМеждуСтарымКоличествомЗадачИНовым(){
        CountTasks counterTasks =  new CountTasks(1, 2, 1);
        CountTasks anotherCounterTasks = new CountTasks(3, 4, 6);

        var differenceCounterTasks = statistic.getCountTasks(counterTasks, anotherCounterTasks);

        assertEquals(9, differenceCounterTasks.countAllTasks);
        assertEquals(2, differenceCounterTasks.countExamples);
        assertEquals(5, differenceCounterTasks.countIssues);
        assertEquals(2, differenceCounterTasks.countSequences);
    }

    @Test
    void getComparativeStatistic_ВозвращаетПроцентПользователейСМеньшимИТемЖеСамымКоличествомРешенныхЗадачВСравненииСДаннымПользователемИКоличествоПользователей(){
        Map<String, UserData> usersData = new HashMap<>();
        UserData firstUser = new UserData("11111");
        UserData secondUser = new UserData("22222");
        UserData thirdUser = new UserData("33333");
        firstUser.setLastStatistic(new CountTasks(1, 2, 1));
        secondUser.setLastStatistic(new CountTasks(3, 4, 6));
        thirdUser.setLastStatistic(new CountTasks(3, 2, 4));
        usersData.put("11111", firstUser);
        usersData.put("22222", secondUser);
        usersData.put("33333", thirdUser);


        var comparativeSecond = statistic.getComparativeStatistic(usersData, secondUser);

        assertEquals(2.0/3*100, comparativeSecond.percentFewer);
        assertEquals(0.0, comparativeSecond.percentSame);
        assertEquals(3, comparativeSecond.countUsers);
    }
}

