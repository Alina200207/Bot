package Main;

import org.glassfish.grizzly.utils.Pair;

import java.util.ArrayList;
import java.util.Objects;

public class ExamplesCommand implements TasksCommand{

    private Example example;

    public void GetLevel(String level){
        if (Objects.equals(level, "3"))
            example = new Example(Tasks.ExamplesLevel3);
        else if (Objects.equals(level, "2"))
             example = new Example(Tasks.ExamplesLevel2);
        else
            example = new Example(Tasks.ExamplesLevel1);
    }
    public String getTask(ArrayList usedTasks){
        return example.getTask(usedTasks);
    }

    public Pair<String, Boolean> getAnswer(String condition, String playerAnswer) {
        var result = example.compareResult(condition, playerAnswer);
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
