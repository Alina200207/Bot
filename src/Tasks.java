import org.glassfish.grizzly.utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public class Tasks {
    public static ArrayList<Pair<String, String>> Issues = new ArrayList<Pair<String, String>>(Arrays.asList(new Pair<String, String>("Многие средневековые русские актёры (скоморохи) " +
            "веселящие народ в ту пору, во время " +
            "своих выступлений использовали погремушки, изготовленные из бычьего пузыря и находящихся " +
            "внутри него плодов одного растения. Плоды, какого растения использовались при изготовлении " +
            "этих погремушек?", "Горох"), new Pair<String, String>("Какое слово начинается на три Г, а кончается на три Я?", "Тригонометрия"),
            new Pair<>("Имя первой женщины в мире, идеально освоившей летательный аппарат.", "Баба Яга"),
        new Pair<String, String>("Это дается нам трижды. Первые два раза бесплатно. А вот за третий придется заплатить.", "Зубы"),
        new Pair<String, String>("Кто говорит на всех языках?", "Эхо"),
        new Pair<String, String>("Москву раньше называли белокаменной. А какой город называли чёрным?", "Чернигов"),
        new Pair<String, String>("Кто может поднять и передвинуть и коня, и слона?", "Шахматист"),
        new Pair<String, String>("Какая гора была самой высокой на Земле до открытия Эвереста?", "Эверест"),
        new Pair<String, String>("Арабы называют эту планету Зухра.", "Венера"),
        new Pair<String, String>("Как называется надпись на монете?", "Легенда"),
        new Pair<String, String>("Крылатый лев с орлиной головой.", "Грифон"),
        new Pair<String, String>("Наука о грибах.", "Микология"),
        new Pair<String, String>("Как переводится слово \"ангел\"?", "Вестник"),
        new Pair<String, String>("Какая птица из сорока букв?", "Сорока"),
        new Pair<String, String>("Что было \"завтра\", а будет \"вчера\"?", "Сегодня"),
        new Pair<String, String>("Какой химический элемент является лесом?", "Бор"),
        new Pair<String, String>("Я хожу, а он остается.", "След"),
        new Pair<String, String>("Всю жизнь ходят в обгонку, а обогнать друг друга не могут.", "Ноги"),
        new Pair<String, String>("Девичья фамилия Агаты Кристи.", "Миллер"),
        new Pair<String, String>("Старое название Парижа.", "Лютеция"),
        new Pair<String, String>("Цветок - символ любви в Древней Греции.", "Боярышник"),
        new Pair<String, String>("Ядом какого цветка аптекарь Лоренцо усыпил Джульетту?", "Лютик")
    ));
    public static ArrayList<Pair<String, String>> Sequences = new ArrayList<Pair<String, String>>(Arrays.asList(
            new Pair<>("1 2 3 4 5", "6"),
            new Pair<>("13 26 39 52", "65"),
            new Pair<>("ab bc cd", "de"),
            new Pair<>("972 875 788 710 639", "576"), //каждое последующее число - это разность его самого и двух его первых цифр
            new Pair<>("2 3 3 5 10 13 39 43 172", "177"), //прибавляем 1, умножаем на 1, прибавляем 2, умножаем на 2 и т.д.
            new Pair<>("a1 b2 c3 d4", "e5"),
            new Pair<>("1 1 2 3 5 8", "13"),
            new Pair<>("13 24 35 46", "57"),
            new Pair<>("П В С Ч П С", "В"),
            new Pair<>("12 43 56", "87"),
            new Pair<>("27 81 243 729", "2187"),
            new Pair<>("", ""),
            new Pair<>("", ""),
            new Pair<>("", ""),
            new Pair<>("", ""),
            new Pair<>("", ""),
            new Pair<>("", ""),
            new Pair<>("", ""),
            new Pair<>("", ""),
            new Pair<>("", "")
    ));
}
