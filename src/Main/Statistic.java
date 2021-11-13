package Main;

import java.util.*;

public class Statistic {
    public String getStatisticWithText(CountTasks countTasks){
        String str = (countTasks.countAllTasks>0) ? "Молодец! Продолжай в том же духе :)"  : "Поднажми! Совсем ведь всё по нулям :(";
        return String.format("Общее количество правильно решенных задач - %s.\nВ частности, примеров - %s, последовательностей - %s, " +
                "загадок - %s.", countTasks.countAllTasks, countTasks.countExamples,
                countTasks.countSequences, countTasks.countIssues) + "\n\n" + str;
    }
    public CountTasks getCountTasks(HashMap<Type.TypeTask, ArrayList<String>> usedTasks){
        return new CountTasks(usedTasks.get(Type.TypeTask.Example).size(),
                usedTasks.get(Type.TypeTask.Sequence).size(), usedTasks.get(Type.TypeTask.Issue).size());
    }
    public CountTasks getCountTasks(CountTasks beforeCount, CountTasks afterCount){
        return new CountTasks(afterCount.countExamples - beforeCount.countExamples,
                afterCount.countSequences - beforeCount.countSequences,
                afterCount.countIssues - beforeCount.countIssues);
    }
}

