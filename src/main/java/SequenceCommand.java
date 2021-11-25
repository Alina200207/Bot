package main.java;

import java.util.ArrayList;

public class SequenceCommand extends TasksCommand {

    public SequenceCommand() {
        super(new WorkingOnTask(Tasks.GetSequences()));
    }
}
