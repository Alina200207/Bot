package main.java.processingTasks;

import main.java.structures.Expression;

public interface IMathTaskApi {
    Expression GetAddTask();
    Expression GetSubTask();
    Expression GetMulTask();
    Expression GetExample(String url);
}
