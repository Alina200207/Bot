package Main;

import java.util.*;

public class Statistic {
    public static String getStatistic(HashMap<Bot.TypeTask, ArrayList<String>> usedTasks){
        Integer countExamples = usedTasks.get(Bot.TypeTask.Example).size();
        Integer countSequences = usedTasks.get(Bot.TypeTask.Sequence).size();
        Integer countIssues = usedTasks.get(Bot.TypeTask.Issue).size();
        Integer countAllTasks = countIssues+countExamples+countSequences;
        String str = (countAllTasks>0) ? "Молодец! Продолжай в том же духе :)"  : "Поднажми! Совсем ведь всё по нулям :(";
        return String.format("Общее количество правильно решенных задач - %s.\nВ частности, примеров - %s, последовательностей - %s, " +
                "загадок - %s.", countAllTasks, countExamples, countSequences, countIssues) + "\n\n" + str;
    }
}
