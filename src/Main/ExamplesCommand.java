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
    @Override
    public void getTaskCommand(WorkingOnTask task) {
        super.getTaskCommand(task);
    }
    public String getTask(ArrayList usedTasks){
        getTaskCommand(example);
        return example.getTask(usedTasks);
    }

    @Override
    public Answer getAnswer(String condition, String playerAnswer) {
        return super.getAnswer(condition, playerAnswer);
    }
}
