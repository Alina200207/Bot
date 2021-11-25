package main.java;

import java.util.ArrayList;

public class ExamplesCommand extends TasksCommand {

    private WorkingOnTask example;

    public ExamplesCommand() {
        super(new WorkingOnTask(Tasks.GetExamplesLevel1()));
    }

    public void AssignLevel(String level){
        if ("3".equals(level))  //сделать какие-то константы для уровня
            setTaskCommand(new WorkingOnTask(Tasks.GetExamplesLevel3()));
        else if ("2".equals(level))
            setTaskCommand(new WorkingOnTask(Tasks.GetExamplesLevel2()));
        else
            setTaskCommand(new WorkingOnTask(Tasks.GetExamplesLevel1()));
    }
}