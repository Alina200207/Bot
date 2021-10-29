package Main;

import org.glassfish.grizzly.utils.Pair;

import java.util.ArrayList;

public class ExamplesCommand implements TasksCommand{

    private WorkingOnTask workingOnTask;

    public void AssignLevel(String level){
        if ("3".equals(level))
            workingOnTask = new WorkingOnTask(Tasks.GetExamplesLevel3());
        else if ("2".equals(level))
             workingOnTask = new WorkingOnTask(Tasks.GetExamplesLevel2());
        else
            workingOnTask = new WorkingOnTask(Tasks.GetExamplesLevel1());
    }
    public String getTask(ArrayList usedTasks){
        return workingOnTask.getTask(usedTasks);
    }

    public Pair<String, Boolean> getAnswer(String condition, String playerAnswer) {
        var result = workingOnTask.compareResult(condition, playerAnswer);
        var str = "Сыграем еще? Выбирай команду:\n" +
                "/issue \n" +
                "/examples \n" +
                "/sequences";
        var message = "";
        if (result.getFirst())
            message = "Верно! \n" + str;
        else message = "Неверно :( \n" +
                String.format("Правильный ответ: %s \n", result.getSecond()) + str;
        return new Pair<>(message, result.getFirst());
    }
}
