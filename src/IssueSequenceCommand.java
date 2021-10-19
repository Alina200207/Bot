import org.glassfish.grizzly.utils.Pair;

import java.util.ArrayList;

public class IssueSequenceCommand {

    private static IssueSequence issue = new IssueSequence(Tasks.Issues);
    private static IssueSequence sequence = new IssueSequence(Tasks.Sequences);

    public static String getCondition(String state, ArrayList usedTasks){
        return state.equals("issue") ? issue.GetCondition(usedTasks) : sequence.GetCondition(usedTasks);
    }

    public static Pair<String, Boolean> giveAnswer(String condition, String playerAnswer, String state){
        var result = state.equals("issue") ? issue.CompareResult(condition, playerAnswer) : sequence.CompareResult(condition, playerAnswer);
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
