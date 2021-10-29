package Main;

import java.util.HashMap;

public class Tasks {

    public static HashMap<String, String>Issues = new HashMap<>();
    public static HashMap<String, String>Sequences = new HashMap<>();
    public static HashMap<String, String>ExamplesLevel1 = new HashMap<>();
    public static HashMap<String, String> ExamplesLevel2 = new HashMap<>();
    public static HashMap<String, String> ExamplesLevel3 = new HashMap<>();

    public static HashMap<String, String> GetIssues() {
        Issues.put("Многие средневековые русские актёры (скоморохи) "+
                "веселящие народ в ту пору, во время " +
                "своих выступлений использовали погремушки, изготовленные из бычьего пузыря и находящихся " +
                "внутри него плодов одного растения. Плоды, какого растения использовались при изготовлении " +
                "этих погремушек?", "Горох");
        Issues.put("Какое слово начинается на три Г, а кончается на три Я?", "Тригонометрия");
                Issues.put("Имя первой женщины в мире, идеально освоившей летательный аппарат.", "Баба Яга");
                Issues.put("Это дается нам трижды. Первые два раза бесплатно. А вот за третий придется заплатить.", "Зубы");
                Issues.put("Кто говорит на всех языках?", "Эхо");
                Issues.put("Москву раньше называли белокаменной. А какой город называли чёрным?", "Чернигов");
                Issues.put("Кто может поднять и передвинуть и коня, и слона?", "Шахматист");
                Issues.put("Какая гора была самой высокой на Земле до открытия Эвереста?", "Эверест");
                Issues.put("Арабы называют эту планету Зухра.", "Венера");
                Issues.put("Как называется надпись на монете?", "Легенда");
                Issues.put("Крылатый лев с орлиной головой.", "Грифон");
                Issues.put("Наука о грибах.", "Микология");
                Issues.put("Как переводится слово \"ангел\" с древнегреческого?", "Вестник");
                Issues.put("Какая птица из сорока букв?", "Сорока");
                Issues.put("Что было \"завтра\", а будет \"вчера\"?", "Сегодня");
                Issues.put("Какой химический элемент является лесом?", "Бор");
                Issues.put("Я хожу, а он остается.", "След");
                Issues.put("Всю жизнь ходят в обгонку, а обогнать друг друга не могут.", "Ноги");
                Issues.put("Девичья фамилия Агаты Кристи.", "Миллер");
                Issues.put("Старое название Парижа.", "Лютеция");
                Issues.put("Цветок - символ любви в Древней Греции.", "Боярышник");
                Issues.put("Ядом какого цветка аптекарь Лоренцо усыпил Джульетту?", "Лютик");
                return Issues;
    }
    public static HashMap<String, String> GetSequences() {
        Sequences.put("1 2 3 4 5", "6");
        Sequences.put("13 26 39 52", "65");
        Sequences.put("ab bc cd", "de");
        Sequences.put("972 875 788 710 639", "576"); //каждое последующее число - это разность его самого и двух его первых цифр
        Sequences.put("2 3 3 5 10 13 39 43 172", "177"); //прибавляем 1, умножаем на 1, прибавляем 2, умножаем на 2 и т.д.
        Sequences.put("a1 b2 c3 d4", "e5");
        Sequences.put("1 1 2 3 5 8", "13");
        Sequences.put("13 24 35 46", "57");
        Sequences.put("П В С Ч П С", "В");
        Sequences.put("12 43 56", "87");
        Sequences.put("27 81 243 729", "2187");
        return  Sequences;
    }
    public static HashMap<String, String> GetExamplesLevel1()
    {
        ExamplesLevel1.put("12 + 3", "15");
        ExamplesLevel1.put("34 + 8", "42");
        ExamplesLevel1.put("87 + 6", "93");
        ExamplesLevel1.put("77 + 6", "83");
        ExamplesLevel1.put("8 / 8", "1");
        ExamplesLevel1.put("121/11", "11");
        ExamplesLevel1.put("6 * 7", "42");
        ExamplesLevel1.put("70 / 10", "7");
        ExamplesLevel1.put("8*10", "80");
        return ExamplesLevel1;
    }

    public static HashMap<String, String> GetExamplesLevel2()
    {
        ExamplesLevel2.put("3*12*4", "144");
        ExamplesLevel2.put("34+17+90", "141");
        ExamplesLevel2.put("23*2+18", "64");
        ExamplesLevel2.put("478+23-45", "456");
        ExamplesLevel2.put("567-896", "-329");
        ExamplesLevel2.put("4958-78", "4880");
        ExamplesLevel2.put("72 + 43", "115");
        ExamplesLevel2.put("476 - 25", "451");
        ExamplesLevel2.put("102000 / 600", "170");
        ExamplesLevel2.put("9600 * 90", "864000");
        ExamplesLevel2.put("34 - 90", "-56");
        return ExamplesLevel2;
    }

    public static HashMap<String, String> GetExamplesLevel3()
    {
        ExamplesLevel3.put("268 + 608 + 72","948");
        ExamplesLevel3.put("98 - 67 + 85","116");
        ExamplesLevel3.put("256 - 34 - 213","9");
        ExamplesLevel3.put("308 + 873 + 415","1596");
        ExamplesLevel3.put("2 + 4 - 13","-7");
        ExamplesLevel3.put("12 * 3 / 4 * 2","18");
        ExamplesLevel3.put("( 7 * 4 + 33 ) – 3 * 6 / 2","5");
        ExamplesLevel3.put("54 / 9 + ( 8 + 19 ) / 3 – 32 / 4","7");
        ExamplesLevel3.put("21 / 7 + (42 – 14 ) / 4 – ( 44 – 14 ) / 5","22");
        return ExamplesLevel3;
    }
}