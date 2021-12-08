package main.java.structures;

import main.java.Bot;

public class Condition {
    public final Bot.State state;
    public final String task;
    public Condition (Bot.State userState, String userTask){
        state = userState;
        task = userTask;
    }
}
