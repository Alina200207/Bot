package main.java;


import main.java.Clue;

public class GetClue {
    public String getClue(String task) {
        var clue = Clue.GetClues();
        System.out.println(clue.get(task));
        return clue.get(task);
    }
}
