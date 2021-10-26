import org.glassfish.grizzly.utils.Pair;

import java.util.ArrayList;
import java.util.Objects;

public class ExamplesCommand {

    private static Example example;
    public static void GetLevel(String level){
        if (Objects.equals(level, "3"))
            example = new Example(Tasks.ExamplesLevel3);
        else if (Objects.equals(level, "2"))
             example = new Example(Tasks.ExamplesLevel2);
        else
            example = new Example(Tasks.ExamplesLevel1);
    }
    public static String getExample(ArrayList usedTasks){
        return example.GetExample(usedTasks);
    }

    public static Pair<String, Boolean> giveAnswer(String condition, String playerAnswer) {
        var result = example.CompareResult(condition, playerAnswer);
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
