package Main;

import java.util.ArrayList;

public class IssueCommand extends TasksCommand{

    private WorkingOnTask issue = new WorkingOnTask(Tasks.GetIssues());

    @Override
    public void getTaskCommand(WorkingOnTask task) {
        super.getTaskCommand(task);
    }

    public String getTask(ArrayList usedTasks){
        getTaskCommand(issue);
        return issue.getTask(usedTasks);
    }

    @Override
    public Answer getAnswer(String condition, String playerAnswer) {
        return super.getAnswer(condition, playerAnswer);
    }
}
