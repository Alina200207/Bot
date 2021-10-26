import org.glassfish.grizzly.utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public class Tasks {
    public static ArrayList<Pair<String, String>> Issues = new ArrayList<Pair<String, String>>(Arrays.asList(new Pair<String, String>("Многие средневековые русские актёры (скоморохи) " +
            "веселящие народ в ту пору, во время " +
            "своих выступлений использовали погремушки, изготовленные из бычьего пузыря и находящихся " +
            "внутри него плодов одного растения. Плоды, какого растения использовались при изготовлении " +
            "этих погремушек?", "Горох"),
            new Pair<String, String>("Какое слово начинается на три Г, а кончается на три Я?", "Тригонометрия"),
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
        new Pair<String, String>("Как переводится слово \"ангел\" с древнегреческого?", "Вестник"),
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
            new Pair<>("27 81 243 729", "2187")
            /*new Pair<>("", ""),
            new Pair<>("", ""),
            new Pair<>("", ""),
            new Pair<>("", ""),
            new Pair<>("", ""),
            new Pair<>("", ""),
            new Pair<>("", ""),
            new Pair<>("", ""),
            new Pair<>("", "")*/
    ));
    public static ArrayList<Pair<String, String>> ExamplesLevel1 = new ArrayList<Pair<String, String>>(Arrays.asList(
            new Pair<>("12 + 3", "15"),
            new Pair<>("34 + 8", "42"),
            new Pair<>("87 + 6", "93"),
            new Pair<>("77 + 6", "83"),
            new Pair<>("8 / 8", "1"),
            new Pair<>("121/11", "11"),
            new Pair<>("6 * 7", "42"),
            new Pair<>("70 / 10", "7"),
            new Pair<>("8*10", "80")
    ));
    public static ArrayList<Pair<String, String>> ExamplesLevel2 = new ArrayList<Pair<String, String>>(Arrays.asList(
            new Pair<>("3*12*4", "144"),
            new Pair<>("34+17+90", "141"),
            new Pair<>("23*2+18", "64"),
            new Pair<>("478+23-45", "456"),
            new Pair<>("567-896", "-329"),
            new Pair<>("4958-78", "4880"),
            new Pair<>("72 + 43", "115"),
            new Pair<>("476 - 25", "451"),
            new Pair<>("102000 / 600", "170"),
            new Pair<>("9600 * 90", "864000"),
            new Pair<>("34 - 90", "-56")
    ));
    public static ArrayList<Pair<String, String>> ExamplesLevel3 = new ArrayList<Pair<String, String>>(Arrays.asList(
            new Pair<>("268 + 608 + 72", "948"),
            new Pair<>("98 - 67 + 85", "116"),
            new Pair<>("256 - 34 - 213", "9"),
            new Pair<>("308 + 873 + 415", "1596"),
            new Pair<>("2 + 4 - 13", "-7"),
            new Pair<>("12 * 3 / 4 * 2", "18"),
            new Pair<>("( 7 * 4 + 33 ) – 3 * 6 / 2", "5"),
            new Pair<>("54 / 9 + ( 8 + 19 ) / 3 – 32 / 4", "7"),
            new Pair<>("21 / 7 + (42 – 14 ) / 4 – ( 44 – 14 ) / 5", "22")
    ));
}
