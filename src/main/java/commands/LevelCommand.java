package main.java.commands;

public class LevelCommand {

    public String getLevel(String currentLevel) {
        if (currentLevel != null)
            return "Ваш текущий уровень: " + currentLevel
                    +"\n Введите 1, 2, 3, чтобы изменить соответствующий уровень";
        return "Ваш текущий уровень: 1"
                +"\n Введите 1, 2, 3, чтобы изменить соответствующий уровень";
    }

    public String getAnswer(String playerAnswer) {
        var message = "Ты ввел неправильную цифру. Попробуй снова /level";
        try{
            var number = Integer.parseInt(playerAnswer);
            if (number<4 && number>0)
                return "Ваш текущий уровень:" + playerAnswer + "\nВыбирай команду:\n";
        }
        catch(NumberFormatException exc){
            return message;
        }
        return message;
    }
}