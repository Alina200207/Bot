package main.java;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Random;

public class WorkingOnTask{ // todo test

    public HashMap<String, String> Tasks;
    public WorkingOnTask(HashMap<String, String> task) {
        Tasks = task;
    }

    public String getTask(ArrayList usedTasks)
    {
        var random = new Random();
        var notUsedTasks = new ArrayList<String>();
        for(var example: Tasks.keySet().toArray()) {
            if (!usedTasks.contains(example)) {
                notUsedTasks.add(example.toString());
            }
        }
        var condition = notUsedTasks.get(random.nextInt(notUsedTasks.size()));
        return condition;
    }
    public Answer compareResult(String condition, String playerAnswer)
    {
        String answer = Tasks.get(condition);
        if (answer!=null && answer.equals(playerAnswer))
            return new Answer(answer, true);
        return new Answer(answer, false);
    }
}