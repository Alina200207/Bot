package main.java.commands;

import main.java.processingTasks.Tasks;
import main.java.processingTasks.WorkingOnTask;

public class SequenceCommand extends TasksCommand {

    public SequenceCommand() {
        super(new WorkingOnTask(Tasks.GetSequences()));
    }
}
