package main.java.commands;

import main.java.processingTasks.MathTasks;
import main.java.processingTasks.Tasks;
import main.java.processingTasks.WorkingOnTask;

import java.util.HashMap;

public class ExamplesCommand extends TasksCommand {
    private String level3 = "3";
    private String level2 = "2";
    private String level1 = "1";
    private final Tasks tasks= new Tasks(new MathTasks());

    private WorkingOnTask example;

    public ExamplesCommand() {
        super(new WorkingOnTask(new Tasks(new MathTasks()).GetExamplesLevel1()));
    }

    public void AssignLevel(String level){
        if (level3.equals(level))
            setTaskCommand(new WorkingOnTask(this.tasks.GetExamplesLevel3()));
        else if (level2.equals(level))
            setTaskCommand(new WorkingOnTask(this.tasks.GetExamplesLevel2()));
        else if (level1.equals(level))
            setTaskCommand(new WorkingOnTask(this.tasks.GetExamplesLevel1()));
    }
}