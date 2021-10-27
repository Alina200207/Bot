package Main;

import org.glassfish.grizzly.utils.Pair;

import java.util.*;

import static java.lang.System.in;

public class IssueSequence implements TasksOperations{

    private ArrayList<Pair<String, String>> IssuesOrSequences;

    public IssueSequence(ArrayList<Pair<String, String>> issues_sequences) {
        IssuesOrSequences = issues_sequences;
    }
    public String getTask(ArrayList usedTasks)
    {
        var random = new Random();
        var notUsedTasks = new ArrayList<Pair<String, String>>();
        for (int i = 0; i < IssuesOrSequences.size(); i++){
            var element = IssuesOrSequences.get(i);
            if (!usedTasks.contains(element.getFirst())){
                notUsedTasks.add(element);
            }
        }
        var condition = notUsedTasks.get(random.nextInt(notUsedTasks.size())).getFirst();
        return condition;
    }

    public Pair<Boolean, String> compareResult(String condition, String playerAnswer)
    {
        String answer = "";
        for (var i = 0; i < IssuesOrSequences.size(); i++)
        {
            if (IssuesOrSequences.get(i).getFirst().equals(condition)) {
                answer = IssuesOrSequences.get(i).getSecond();
                return new Pair<>(answer.equals(playerAnswer), answer);
            }
        }
        return new Pair<>(false, answer);
    }



}
