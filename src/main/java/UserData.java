package main.java;

import main.java.Bot;
import main.java.Condition;
import main.java.CountTasks;
import main.java.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class UserData {
    private final String id;
    private Condition condition;
    private HashMap<Type.TypeTask, ArrayList<String>> usedTasks;
    private String level;
    private CountTasks lastStatistic;
    public UserData(String id){
        this.id = id;
        this.condition = new Condition(Bot.State.NoState, "");
        this.level = "1";
        this.lastStatistic = new CountTasks(0, 0, 0);
        this.usedTasks = new HashMap<>();
        this.usedTasks.put(Type.TypeTask.Example, new ArrayList<>());
        this.usedTasks.put(Type.TypeTask.Sequence, new ArrayList<>());
        this.usedTasks.put(Type.TypeTask.Issue, new ArrayList<>());
    }
    public String getId(){
        return  this.id;
    }
    public CountTasks getLastStatistic(){
        return this.lastStatistic;
    }
    public void setLastStatistic(CountTasks statistic){
        this.lastStatistic = statistic;
    }
    public HashMap<Type.TypeTask, ArrayList<String>> getUsedTasks(){
        return this.usedTasks;
    }
    public void ChangeUsedTasks(Type.TypeTask type, String conditionTask){
        ArrayList<String> usedTasks = this.usedTasks.get(type);
        usedTasks.add(conditionTask);
        this.usedTasks.put(type, usedTasks);
    }
    public Condition getCondition(){
        return this.condition;
    }
    public void setCondition(Bot.State state, String conditionTask){
        this.condition = new Condition(state, conditionTask);
    }
    public String getLevel(){
        return this.level;
    }
    public void setLevel(String level){
        try {
            int number = Integer.parseInt(level);
            if (number > 0 && number < 4) {
                this.level = Integer.toString(number);
            }
        }
        catch(NumberFormatException exc) {
            System.out.println(exc.getMessage());
        }
    }

}
