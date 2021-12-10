package main.java.processingTasks;

import main.java.Api;
import main.java.structures.Expression;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MathTasks implements IMathTaskApi{
    public Expression GetAddTask(){
        var url = "https://x-math.herokuapp.com/api/add";
        return GetExample(url);
    }
    public Expression GetSubTask(){
        var url = "https://x-math.herokuapp.com/api/sub";
        return GetExample(url);
    }
    public Expression GetMulTask(){
        var url =  "https://x-math.herokuapp.com/api/mul";
        return GetExample(url);
    }

    public Expression GetExample(String url) {
        var a = new Api();
        Expression expression = new Expression("", "");
        try {
            expression = a.GetExampleWithApi(url);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return expression;
    }
}
