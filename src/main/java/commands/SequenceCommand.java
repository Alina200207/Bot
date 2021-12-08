package main.java.commands;

import main.java.Tasks;
import main.java.WorkingOnTask;

public class SequenceCommand extends TasksCommand {

    public SequenceCommand() {
        super(new WorkingOnTask(Tasks.GetSequences()));
    }
}
