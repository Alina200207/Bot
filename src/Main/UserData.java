package Main;

import java.util.ArrayList;
import java.util.HashMap;

public class UserData {
    private final String Id;
    private Condition Condition;
    private HashMap<Type.TypeTask, ArrayList<String>> UsedTasks;
    private String Level;
    private CountTasks LastStatistic;
    public UserData(String id){
        this.Id = id;
        this.Condition = new Condition(Bot.State.NoState, "");
        this.Level = "1";
        this.LastStatistic = new CountTasks(0, 0, 0);
        this.UsedTasks = new HashMap<>();
        this.UsedTasks.put(Type.TypeTask.Example, new ArrayList<>());
        this.UsedTasks.put(Type.TypeTask.Sequence, new ArrayList<>());
        this.UsedTasks.put(Type.TypeTask.Issue, new ArrayList<>());
    }
    public CountTasks GetLastStatistic(){
        return this.LastStatistic;
    }
    public void SetLastStatistic(CountTasks statistic){
        this.LastStatistic = statistic;
    }
    public HashMap<Type.TypeTask, ArrayList<String>> GetUsedTasks(){
        return this.UsedTasks;
    }
    public void ChangeUsedTasks(Type.TypeTask type, String conditionTask){
        ArrayList<String> usedTasks = this.UsedTasks.get(type);
        usedTasks.add(conditionTask);
        this.UsedTasks.put(type, usedTasks);
    }
    public Condition GetCondition(){
        return this.Condition;
    }
    public void SetCondition(Bot.State state, String conditionTask){
        this.Condition = new Condition(state, conditionTask);
    }
    public String GetLevel(){
        return this.Level;
    }
    public void SetLevel(String level){
        try {
            int number = Integer.parseInt(level);
            if (number > 0 && number < 4) {
                this.Level = Integer.toString(number);
            }
        }
        catch(NumberFormatException exc) {
            System.out.println(exc.getMessage());
        }
    }

}
