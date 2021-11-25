package main.java;

import java.util.ArrayList;

public class IssueCommand extends TasksCommand {
    public IssueCommand() {
        super(new WorkingOnTask(Tasks.GetIssues()));
    }
}
