package Main;

import java.util.ArrayList;

public class ExamplesCommand extends TasksCommand{

    private WorkingOnTask example;

    public void AssignLevel(String level){
        if ("3".equals(level))
            example = new WorkingOnTask(Tasks.GetExamplesLevel3());
        else if ("2".equals(level))
            example = new WorkingOnTask(Tasks.GetExamplesLevel2());
        else
            example = new WorkingOnTask(Tasks.GetExamplesLevel1());
    }

    public String getTask(ArrayList usedTasks){
        setTaskCommand(example);
        return example.getTask(usedTasks);
    }
}