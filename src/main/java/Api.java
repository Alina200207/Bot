package main.java;

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

        return expression;
    }
}