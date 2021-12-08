package main.java.commands;

import main.java.Answer;
import main.java.WorkingOnTask;

import java.util.ArrayList;

public class TasksCommand {
    public WorkingOnTask workingOnTask;

    public TasksCommand(WorkingOnTask task) {
        this.workingOnTask = task;
    }

    public String getTask(ArrayList usedTasks) {
        return workingOnTask.getTask(usedTasks);
    }

    public void setTaskCommand(WorkingOnTask task) {
        this.workingOnTask = task;
    }

    public Answer getAnswer(String condition, String playerAnswer){
        return workingOnTask.compareResult(condition, playerAnswer);
    }

    public Answer getAnswerWithText(Answer answer){
        var str = "Сыграем еще?";
        var message = "";
        if (answer.correctness)
            message = "Верно! \n" + str;
        else message = "Неверно :( \n" +
                String.format("Правильный ответ: %s \n", answer.answerString) + str;
        return new Answer(message, answer.correctness);
    }
}
