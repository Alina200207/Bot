package Main;

import org.glassfish.grizzly.utils.Pair;

import java.util.ArrayList;

public interface TasksCommand {
    public String getTask(ArrayList usedTasks);
    public Pair<String, Boolean> getAnswer(String condition, String playerAnswer);
}
