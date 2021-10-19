import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.glassfish.grizzly.utils.Pair;

import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {

    public enum State{
        NoState,
        Issue,
        Example,
        Sequence,
        Level
    };


    private Map<String, Pair<State, String>> UsersCondition = new HashMap<>();
    private static HashMap<String, String> Level = new HashMap<>();

    @Override
    public String getBotToken() {
        return "1983849094:AAGFJrDTH5oOpPdhANCU97k9a_SNagBQwo4";
    }

    @Override
    public String getBotUsername() {
        return "@Pump_up_your_brain_bot";
    }


    @Override
    public void onUpdateReceived(Update update) {
        Long chat_Id = update.getMessage().getChatId();
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chat_Id));
        if(update.getMessage()!=null && update.getMessage().hasText()) {
            String inputText = update.getMessage().getText();
            switch (inputText) {
                case "/start":
                    message.setText(StartCommand.start());
                    break;
                case "/help":
                    message.setText(HelpCommand.giveHelp());
                    break;
                case "/examples":
                    var level = Level.get(chat_Id.toString());
                    ExamplesCommand.GetLevel(level);
                    message.setText(ExamplesCommand.getExample());
                    UsersCondition.put(chat_Id.toString(), new Pair<>(State.Example, message.getText()));
                    break;
                case "/sequences":
                    message.setText(StartCommand.start());
                    break;
                case "/issue":
                    message.setText(IssueCommand.getIssue());
                    UsersCondition.put(chat_Id.toString(), new Pair<>(State.Issue, message.getText()));
                    break;
                 case "/level":
                    message.setText(LevelCommand.getLevel(Level.get(chat_Id.toString())));
                    Level.put(chat_Id.toString(), message.getText());
                    UsersCondition.put(chat_Id.toString(), new Pair<>(State.Level, message.getText()));
                    break;
                //case "/statistic":
                default:
                    if (UsersCondition.get(chat_Id.toString())!=null) {
                        Pair<State, String> condition = UsersCondition.get(chat_Id.toString());
                        switch (condition.getFirst()) {
                            case Issue:
                                message.setText(IssueCommand.giveAnswer(condition.getSecond(), inputText));
                                break;
                            case Example:
                                message.setText(ExamplesCommand.giveAnswer(condition.getSecond(), inputText));
                                break;
                            case Level:
                                message.setText(LevelCommand.giveAnswer(inputText));
                                Level.put(chat_Id.toString(), inputText);
                                break;
                        }
                    }
                    UsersCondition.put(chat_Id.toString(), new Pair<>(State.NoState, ""));
                    break;
            }
        }
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
