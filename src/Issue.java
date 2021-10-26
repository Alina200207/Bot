import org.glassfish.grizzly.utils.Pair;

import java.util.*;

public class Issue {

    public static ArrayList<Pair<String, String>> Issues;

    public Issue(ArrayList<Pair<String, String>> issues) {
        Issues = issues;
    }
    public String GetIssue()
    {
        var random = new Random();
        var condition = Issues.get(random.nextInt(Issues.size())).getFirst();
        return condition;
    }

    public Pair<Boolean, String> CompareResult(String condition, String playerAnswer)
        {
            String answer = "";
            for (var i = 0; i < Issues.size(); i++)
            {
                if (Issues.get(i).getFirst().equals(condition)) {
                    answer = Issues.get(i).getSecond();
                    return new Pair<>(answer.equals(playerAnswer), answer);
                }
            }
            return new Pair<>(false, answer);
    }



}
