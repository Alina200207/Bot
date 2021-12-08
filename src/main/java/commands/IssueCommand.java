package main.java.commands;

import main.java.processingTasks.Tasks;
import main.java.processingTasks.WorkingOnTask;

public class IssueCommand extends TasksCommand {
    public IssueCommand() {
        super(new WorkingOnTask(Tasks.GetIssues()));
    }
}
