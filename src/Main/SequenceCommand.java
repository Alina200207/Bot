package Main;

import java.util.ArrayList;

public class SequenceCommand extends TasksCommand{

    private WorkingOnTask sequence = new WorkingOnTask(Tasks.GetSequences());

    public String getTask(ArrayList usedTasks){
        setTaskCommand(sequence);
        return sequence.getTask(usedTasks);
    }
}
