public class LevelCommand {

    public static String getLevel(String currentLevel) {
        if (currentLevel != null)
            return "Ваш текущий уровень: " + currentLevel
                    +"\n Введите 1, 2, 3, чтобы изменить соответствующий уровень";
        return "Ваш текущий уровень: 1"
                +"\n Введите 1, 2, 3, чтобы изменить соответствующий уровень";
    }

    public static String giveAnswer(String playerAnswer) {
        if (Integer.parseInt(playerAnswer)>3)
            return "Ты ввел неправильную цифру. Попробуй снова /level";
        var message = "Ваш текущий уровень:" + playerAnswer + "\nВыбирай команду:\n" +
                "/issue \n" +
                "/examples \n" +
                "/sequences";
        return message;
    }
}