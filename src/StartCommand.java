import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

// interface ICommand {
// public String handle();
// }

public class StartCommand {

    public static String start() {
        return ("Привет! Я бот, который поможет прокачать твой мозг. \n \nВ зависимости от твоего " +
                "выбора я вышлю тебе либо загадки, либо последовательности, либо примеры." +
                "\n \nТебе предстоит выполнить соответствующие задания за ограниченное время." +
                " Чтобы ознакомиться со списком команд нажми на /help");
    }

}
