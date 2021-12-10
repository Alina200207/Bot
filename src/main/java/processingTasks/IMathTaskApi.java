package main.java.processingTasks;

import java.util.ArrayList;
import java.util.HashMap;

public interface IMathTaskApi {
    ArrayList<String> GetAddTask(HashMap<String, String> examplesLevel);
    ArrayList<String> GetSubTask(HashMap<String, String> examplesLevel);
    ArrayList<String> GetMulTask(HashMap<String, String> examplesLevel);
    ArrayList<String> GetExample(String url);
}
