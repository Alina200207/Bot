package Main;

import java.util.ArrayList;

public class IssueCommand extends TasksCommand{

    private WorkingOnTask issue = new WorkingOnTask(Tasks.GetIssues());

    public String getTask(ArrayList usedTasks){
        setTaskCommand(issue);
        return issue.getTask(usedTasks);
    }
}