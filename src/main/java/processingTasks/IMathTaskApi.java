package main.java.processingTasks;

import main.java.Api;

import java.io.IOException;
import java.util.HashMap;

public interface IMathTaskApi {
    HashMap<String, String> GetAddTask(HashMap<String, String> examplesLevel);
    HashMap<String, String> GetSubTask(HashMap<String, String> examplesLevel);
    HashMap<String, String> GetMulTask(HashMap<String, String> examplesLevel);
    HashMap<String, String> GetExample(HashMap<String, String> examplesLevel, String url);
}
