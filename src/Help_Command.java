import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Help_Command {
    public static String giveHelp() {
        String helpMessage = new String("Я бот, который поможет прокачать мозг. " +
                "В зависимости от твоего выбора я отправлю тебе либо загадки, либо последовательности, либо примеры. Обрати внимание: " +
                "задание необходимо выполнить за ограниченное время(будет указано для каждого уровня сложности отдельно)\n\n" +
                "Список команд:\n" +
                "/examples - математические примеры\n" +
                "Дается пример, который необходимо решить. Введи свой ответ и бот проверит его правильность.\n" +
                "/sequences - последовательности\n" +
                "Догадайся по какому правилу формируется последовательность и продолжи ее.\n" +
                "/issue - загадка\n" +
                "Отгадай загадку и введи свой ответ с большой буквы в именительном падеже.\n" +
                "/help - помощь\n" +
                "/level - уровень сложности\n" +
                "По умолчанию бот будет отправлять задания самого легкого уровня. Чтобы сменить уровень вызови эту команду.\n" +
                "Желаем удачи :)");
        //SendMessage sendNewMessage = new SendMessage();
        //sendNewMessage.setChatId(chat_Id);
        //sendNewMessage.setText(helpMessage);
        return helpMessage;
    }
}