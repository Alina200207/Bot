package Main;


public class GetClue {
    public String getClue(String task) {
        var clue = Clue.GetClues();
        System.out.println(clue.get(task));
        return clue.get(task);
    }
}
