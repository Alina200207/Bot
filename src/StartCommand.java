import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


public class StartCommand {

    public static SendMessage start( Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Привет! Я бот, который поможет прокачать твой мозг. В зависимости от твоего " +
                "выбора я вышлю тебе либо загадки, либо последовательности, либо примеры." +
                " Тебе предстоит выполнить соответствующие задания за ограниченное время." +
                " Чтобы ознакомиться со списком команд нажми на /help");
        return message;
    }

}
