import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.glassfish.grizzly.utils.Pair;

import java.util.ArrayList;
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
    public enum TypeTask{
        Issue,
        Sequence,
        Example
    }

    private Map<String, Pair<State, String>> UsersCondition = new HashMap<>();
    private Map<String, HashMap<TypeTask, ArrayList<String>>> UsersUsedTasks = new HashMap<>();

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
        message.setText("oh, no!");
        if (UsersUsedTasks.get(chat_Id.toString())==null) {
            UsersUsedTasks.put(chat_Id.toString(), new HashMap<TypeTask, ArrayList<String>>());
            UsersUsedTasks.get(chat_Id.toString()).put(TypeTask.Example, new ArrayList<>());
            UsersUsedTasks.get(chat_Id.toString()).put(TypeTask.Sequence, new ArrayList<>());
            UsersUsedTasks.get(chat_Id.toString()).put(TypeTask.Issue, new ArrayList<>());
        }
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
                    message.setText(StartCommand.start());
                    break;
                case "/sequences":
                    message.setText(IssueSequenceCommand.getCondition("sequence", UsersUsedTasks.get(chat_Id.toString()).get(TypeTask.Sequence)));
                    UsersCondition.put(chat_Id.toString(), new Pair<>(State.Sequence, message.getText()));
                    break;
                case "/issue":
                    message.setText(IssueSequenceCommand.getCondition("issue", UsersUsedTasks.get(chat_Id.toString()).get(TypeTask.Issue)));
                    UsersCondition.put(chat_Id.toString(), new Pair<>(State.Issue, message.getText()));
                    break;
                case "/level":
                    message.setText(StartCommand.start());
                    break;
                case "/statistic":
                    message.setText(Statistic.getStatistic(UsersUsedTasks.get(chat_Id.toString())));
                    break;
                default:
                    if (UsersCondition.get(chat_Id.toString())!=null) {
                        Pair<State, String> condition = UsersCondition.get(chat_Id.toString());
                        HashMap<TypeTask, ArrayList<String>> usedTasks = UsersUsedTasks.get(chat_Id.toString());
                        System.out.println(usedTasks);
                        var answer = new Pair<String, Boolean>();
                        switch (condition.getFirst()) {
                            case Issue:
                                answer = IssueSequenceCommand.giveAnswer(condition.getSecond(), inputText, "issue");
                                ArrayList<String> usedIssues = usedTasks.get(TypeTask.Issue);
                                message.setText(answer.getFirst());
                                if (answer.getSecond()) {
                                    usedIssues.add(condition.getSecond());
                                    UsersUsedTasks.get(chat_Id.toString()).put(TypeTask.Issue, usedIssues);
                                    System.out.println(UsersUsedTasks);
                                }
                                break;
                            case Sequence:
                                answer = IssueSequenceCommand.giveAnswer(condition.getSecond(), inputText, "sequence");
                                message.setText(answer.getFirst());
                                ArrayList<String> usedSequences = usedTasks.get(TypeTask.Sequence);
                                if (answer.getSecond()) {
                                    usedSequences.add(condition.getSecond());
                                    UsersUsedTasks.get(chat_Id.toString()).put(TypeTask.Sequence, usedSequences);
                                }
                                break;
                        }
                    }
                    else message.setText("Не понимаю тебя( \nЧтобы просмотреть список команд введи /help.");

                    System.out.println(UsersUsedTasks);
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
