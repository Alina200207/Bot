package Tests;

import Main.Bot;
import Main.Statistic;
import Main.Type;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import java.util.ArrayList;

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
            ArrayList<String> examples = new ArrayList<>();
            examples.add("23+89");
            usedTasks.put(Type.TypeTask.Issue, issues);
            usedTasks.put(Type.TypeTask.Sequence, sequences);
            usedTasks.put(Type.TypeTask.Example, examples);
        }

        @Test
        void getStatisticTest(){
            assertEquals("Общее количество правильно решенных задач - 3." +
                    "\n" + "В частности, примеров - 1, " +
                    "последовательностей - 1, "  +
                    "загадок - 1." + "\n\n" + "Молодец! Продолжай в том же духе :)", statistic.getStatistic(usedTasks));
        }
    }