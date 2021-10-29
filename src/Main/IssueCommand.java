package Main;

import org.glassfish.grizzly.utils.Pair;

import java.util.ArrayList;

public class IssueCommand implements TasksCommand{

    private static WorkingOnTask issue = new WorkingOnTask(Tasks.GetIssues());

    public String getTask(ArrayList usedTasks){
        return issue.getTask(usedTasks);
    }

    public Pair<String, Boolean> getAnswer(String condition, String playerAnswer){
        var result = issue.compareResult(condition, playerAnswer);
        var str = "Сыграем еще? Выбирай команду:\n" +
                "/issue \n" +
                "/examples \n" +
                "/sequences";
        var message = "";
        if (result.getFirst())
            message = "Верно! \n" + str;
        else message = "Неверно :( \n" +
                String.format("Правильный ответ: %s \n", result.getSecond()) + str;
        return new Pair(message, result.getFirst());
    }
}
