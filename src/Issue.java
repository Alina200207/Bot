import org.glassfish.grizzly.utils.Pair;

import java.util.*;

public class Issue {

    private ArrayList<Pair<String, String>> Issues;

    public Issue() {
        Issues = new ArrayList<Pair<String, String>>(){

        };
        Issues.add(new Pair<>("Многие средневековые русские актёры (скоморохи) веселящие народ в ту пору, во время " +
                "своих выступлений использовали погремушки, изготовленные из бычьего пузыря и находящихся " +
                "внутри него плодов одного растения. Плоды, какого растения использовались при изготовлении " +
                "этих погремушек?", "Горох"));
        Issues.add(new Pair<>("Какое слово начинается на три Г, а кончается на три Я?", "Тригонометрия"));
        Issues.add(new Pair<>("Имя первой женщины в мире, идеально освоившей летательный аппарат.", "Баба Яга"));
        Issues.add(new Pair<>("Это дается нам трижды. Первые два раза бесплатно. А вот за третий придется заплатить.", "Зубы"));
        Issues.add(new Pair<>("Кто говорит на всех языках?", "Эхо"));
        Issues.add(new Pair<>("Москву раньше называли белокаменной. А какой город называли чёрным?", "Чернигов"));
        Issues.add(new Pair<>("Кто может поднять и передвинуть и коня, и слона?", "Шахматист"));
        Issues.add(new Pair<>("Какая гора была самой высокой на Земле до открытия Эвереста?", "Эверест"));
    }
    public String GetIssue()
    {
        var random = new Random();
        var condition = Issues.get(random.nextInt(Issues.size())).getFirst();
        return condition;
    }

    public Boolean CompareResult(String condition, String playerAnswer)
    {
        for (var i = 0; i < Issues.size(); i++)
        {
            if (Issues.get(i).getFirst().equals(condition))
                return Issues.get(i).getSecond().equals(playerAnswer);
        }
        return false;
    }



}
