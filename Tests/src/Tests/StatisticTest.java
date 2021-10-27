package Tests;

import Main.Bot;
import Main.Statistic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticTest {
        private static HashMap<Bot.TypeTask, ArrayList<String>> usedTasks = new HashMap<>();

        @BeforeAll
        private static void setUp() {
            ArrayList<String> issues = new ArrayList<>();
            issues.add("Висит груша, нельзя скушать");
            ArrayList<String> sequences = new ArrayList<>();
            sequences.add("45 54 45 54");
            ArrayList<String> examples = new ArrayList<>();
            examples.add("23+89");
            usedTasks.put(Bot.TypeTask.Issue, issues);
            usedTasks.put(Bot.TypeTask.Sequence, sequences);
            usedTasks.put(Bot.TypeTask.Example, examples);
        }

        @Test
        void getStatisticTest(){
            assertEquals("Общее количество правильно решенных задач - 3." +
                    "\n" + "В частности, примеров - 1, " +
                    "последовательностей - 1, "  +
                    "загадок - 1." + "\n\n" + "Молодец! Продолжай в том же духе :)", Statistic.getStatistic(usedTasks));
        }
    }