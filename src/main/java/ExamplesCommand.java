package main.java;

import java.util.ArrayList;

public class ExamplesCommand extends TasksCommand {
    private String level3 = "3";
    private String level2 = "2";
    private String level1 = "1";

    private WorkingOnTask example;

    public ExamplesCommand() {
        super(new WorkingOnTask(Tasks.GetExamplesLevel1()));
    }

    public void AssignLevel(String level){
        if (level3.equals(level))
            setTaskCommand(new WorkingOnTask(Tasks.GetExamplesLevel3()));
        else if (level2.equals(level))
            setTaskCommand(new WorkingOnTask(Tasks.GetExamplesLevel2()));
        else if (level1.equals(level))
            setTaskCommand(new WorkingOnTask(Tasks.GetExamplesLevel1()));
    }
}