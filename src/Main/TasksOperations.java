package Main;

import org.glassfish.grizzly.utils.Pair;

import java.util.ArrayList;

public interface TasksOperations {
    public String getTask(ArrayList usedTasks);
    public Pair<Boolean, String> compareResult(String condition, String playerAnswer);
}
