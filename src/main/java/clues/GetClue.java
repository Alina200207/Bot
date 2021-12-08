package main.java.clues;

public class GetClue {
    public String getClue(String task) {
        var clue = Clue.GetClues();
        return clue.get(task);
    }
}
