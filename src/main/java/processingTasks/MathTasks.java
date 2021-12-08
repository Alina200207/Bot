package main.java.processingTasks;

import main.java.Api;

import java.io.IOException;
import java.util.HashMap;

public class MathTasks implements IMathTaskApi{
    public HashMap<String, String> GetAddTask(HashMap<String, String> examplesLevel){
        var url = "https://x-math.herokuapp.com/api/add";
        return GetExample(examplesLevel, url);
    }
    public HashMap<String, String> GetSubTask(HashMap<String, String> examplesLevel){
        var url = "https://x-math.herokuapp.com/api/sub";
        return GetExample(examplesLevel, url);
    }
    public HashMap<String, String> GetMulTask(HashMap<String, String> examplesLevel){
        var url =  "https://x-math.herokuapp.com/api/mul";
        return GetExample(examplesLevel, url);
    }

    public HashMap<String, String> GetExample(HashMap<String, String> examplesLevel, String url) {
        var a = new Api();
        try {
            var expression = a.GetExampleWithApi(url);
            examplesLevel.put(expression.get(0), expression.get(1));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return examplesLevel;
    }
}
