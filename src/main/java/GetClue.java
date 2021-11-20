package main.java;

public class GetClue {
    public String getClue(String task) {
        var clue = Clue.GetClues();
        return clue.get(task);
    }
}
