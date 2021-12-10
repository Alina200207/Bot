package main.java.processingTasks;

import main.java.structures.Expression;

import java.util.ArrayList;
import java.util.HashMap;

public interface IMathTaskApi {
    Expression GetAddTask();
    Expression GetSubTask();
    Expression GetMulTask();
    Expression GetExample(String url);
}
