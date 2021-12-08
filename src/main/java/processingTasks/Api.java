package main.java.processingTasks;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.*;

public class Api {
    public ArrayList<String> GetExampleWithApi(String u) throws IOException {
        var expression = new ArrayList<String>();
<<<<<<< HEAD
        URL url = new URL(u);
        URLConnection request = url.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootObj = root.getAsJsonObject();
        String message = rootObj.get("expression").getAsString();
        String answer = rootObj.get("answer").getAsString();
        expression.add(message);
        expression.add(answer);
=======
        var connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        var is = connection.getInputStream();
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        var task = new String(bytes);
        var t = task.split(",");
        expression.add(t[3].split("\"")[3]);
        expression.add(t[4].split(":")[1].replace("}", ""));
        var kuku = "kuku";
        System.out.println(kuku);
>>>>>>> b70adac1e842a657a3791375900dd0e00da4bd88
        return expression;
    }
}