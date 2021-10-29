package Main;

import java.util.ArrayList;

public class SequenceCommand extends TasksCommand{

    private WorkingOnTask sequence = new WorkingOnTask(Tasks.GetSequences());

    @Override
    public void getTaskCommand(WorkingOnTask task) {
        super.getTaskCommand(task);
    }

    public String getTask(ArrayList usedTasks){
        getTaskCommand(sequence);
        return sequence.getTask(usedTasks);
    }

    @Override
    public Answer getAnswer(String condition, String playerAnswer) {
        return super.getAnswer(condition, playerAnswer);
    }
}
