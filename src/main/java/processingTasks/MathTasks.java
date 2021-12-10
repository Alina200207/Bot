package main.java.processingTasks;

import main.java.Api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MathTasks implements IMathTaskApi{
    public ArrayList<String> GetAddTask(HashMap<String, String> examplesLevel){
        var url = "https://x-math.herokuapp.com/api/add";
        return GetExample(url);
    }
    public ArrayList<String> GetSubTask(HashMap<String, String> examplesLevel){
        var url = "https://x-math.herokuapp.com/api/sub";
        return GetExample(url);
    }
    public ArrayList<String> GetMulTask(HashMap<String, String> examplesLevel){
        var url =  "https://x-math.herokuapp.com/api/mul";
        return GetExample(url);
    }

    public ArrayList<String> GetExample(String url) {
        var a = new Api();
        var examplesLevel = new ArrayList<String>();
        try {
            var expression = a.GetExampleWithApi(url);
            examplesLevel.add(expression.get(0));
            examplesLevel.add(expression.get(1));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return examplesLevel;
    }
}
