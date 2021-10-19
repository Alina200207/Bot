import org.glassfish.grizzly.utils.Pair;

import java.util.*;

public class Statistic {
    public static String getStatistic(HashMap<Bot.TypeTask, ArrayList<String>> usedTasks){
        Integer countExamples = usedTasks.get(Bot.TypeTask.Example).size();
        Integer countSequences = usedTasks.get(Bot.TypeTask.Sequence).size();
        Integer countIssues = usedTasks.get(Bot.TypeTask.Issue).size();
        String str = (countIssues+countExamples+countSequences>0) ? "Молодец! Продолжай в том же духе :)"  : "Поднажми! Совсем ведь всё по нулям :(";
        return String.format("Общее количество правильно решенных задач - %s.", countExamples + countIssues + countSequences) +
                "\n" + String.format("В частности, примеров - %s, ", countExamples) +
                String.format("последовательностей - %s, ", countSequences)  +
                String.format("загадок - %s.", countIssues) + "\n\n" + str;
    }
}
