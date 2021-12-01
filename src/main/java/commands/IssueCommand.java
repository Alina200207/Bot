package main.java.commands;

import main.java.Tasks;
import main.java.WorkingOnTask;

public class IssueCommand extends TasksCommand {
    public IssueCommand() {
        super(new WorkingOnTask(Tasks.GetIssues()));
    }
}
