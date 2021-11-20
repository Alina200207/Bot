package main.java;

import main.java.Answer;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Random;

public class WorkingOnTask{

    public HashMap<String, String> Task;
    public WorkingOnTask(HashMap<String, String> task) {
        Task = task;
    }

    public String getTask(ArrayList usedTasks)
    {
        var random = new Random();
        var notUsedTasks = new ArrayList<String>();
        for(var example: Task.keySet().toArray()) {
            if (!usedTasks.contains(example)) {
                notUsedTasks.add(example.toString());
            }
        }
        var condition = notUsedTasks.get(random.nextInt(notUsedTasks.size()));
        return condition;
    }
    public Answer compareResult(String condition, String playerAnswer)
    {
        String answer = Task.get(condition);
        if (answer.equals(playerAnswer))
            return new Answer(answer, true);
        return new Answer(answer, false);
    }
}