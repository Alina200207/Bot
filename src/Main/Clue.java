package Main;

import java.util.HashMap;

public class Clue {
    public static HashMap<String, String> Clues = new HashMap<>();
    public static HashMap<String, String> GetClues() {
        Clues.put("1 2 3 4 5", "Подсказка");
        Clues.put("13 26 39 52", "Подсказка");
        Clues.put("ab bc cd", "Подсказка");
        Clues.put("972 875 788 710 639", "каждое последующее число - это разность его самого и двух его первых цифр");
        Clues.put("2 3 3 5 10 13 39 43 172", "прибавляем 1, умножаем на 1, прибавляем 2, умножаем на 2 и т.д.");
        Clues.put("a1 b2 c3 d4", "Подсказка");
        Clues.put("1 1 2 3 5 8", "Подсказка");
        Clues.put("13 24 35 46", "Подсказка");
        Clues.put("П В С Ч П С", "Подсказка");
        Clues.put("12 43 56", "Подсказка");
        Clues.put("27 81 243 729", "Подсказка");
        return  Clues;
    }
}
