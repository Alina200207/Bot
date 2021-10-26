import org.glassfish.grizzly.utils.Pair;

import java.util.ArrayList;

import java.util.Random;

public class Example {

    public static ArrayList<Pair<String, String>> Examples;
    public Example(ArrayList<Pair<String, String>> examples) {
        Examples = examples;
    }

    public String GetExample(ArrayList usedTasks)
    {
        var random = new Random();
        var notUsedTasks = new ArrayList<Pair<String, String>>();
        for (int i = 0; i < Examples.size(); i++){
            var element = Examples.get(i);
            if (!usedTasks.contains(element.getFirst())){
                notUsedTasks.add(element);
            }
        }
        var condition = notUsedTasks.get(random.nextInt(notUsedTasks.size())).getFirst();
        return condition;
    }
    public Pair<Boolean, String> CompareResult(String condition, String playerAnswer)
    {
        String answer = "";
        for (var i = 0; i < Examples.size(); i++)
        {
            if (Examples.get(i).getFirst().equals(condition)) {
                answer = Examples.get(i).getSecond();
                return new Pair<>(answer.equals(playerAnswer), answer);
            }
        }
        return new Pair<>(false, answer);
    }
}
