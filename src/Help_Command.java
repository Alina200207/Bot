import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Help_Command {
    public static SendMessage giveHelp(String chat_Id) {
        String helpMessage = new String("Я бот, который поможет прокачать мозг. " +
                "В зависимости от твоего выбора я отправлю тебе либо загадки, либо последовательности, либо примеры. Обрати внимание: " +
                "задание необходимо выполнить за ограниченное время(будет указано для каждого уровня сложности отдельно)" +
                System.lineSeparator() +
                System.lineSeparator() +
                "Список команд:" +
                System.lineSeparator() +
                "/examples - математические примеры" +
                System.lineSeparator() +
                "Дается пример, который необходимо решить. Введи свой ответ и бот проверит его правильность." +
                System.lineSeparator() +
                "/sequences - последовательности" +
                System.lineSeparator() +
                "Догадайся по какому правилу формируется последовательность и продолжи ее." +
                System.lineSeparator() +
                "/issue - загадка" +
                System.lineSeparator() +
                "Отгадай загадку и введи свой ответ с большой буквы в именительном падеже." +
                System.lineSeparator() +
                "/help - помощь" +
                System.lineSeparator() +
                "/level - уровень сложности" +
                System.lineSeparator() +
                "По умолчанию бот будет отправлять задания самого легкого уровня. Чтобы сменить уровень вызови эту команду." +
                System.lineSeparator() +
                System.lineSeparator() +
                "Желаем удачи :)");
        SendMessage sendNewMessage = new SendMessage();
        sendNewMessage.setChatId(chat_Id);
        sendNewMessage.setText(helpMessage);
        return sendNewMessage;
    }
}