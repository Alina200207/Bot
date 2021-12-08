package main.java;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.*;

public class Api {
    public ArrayList<String> GetExampleWithApi(String url) throws IOException {
        var expression = new ArrayList<String>();
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
        return expression;
    }
}