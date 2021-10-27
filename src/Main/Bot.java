package Main;

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
    private static HashMap<String, String> Level = new HashMap<>();
    private IssueCommand issueCommand = new IssueCommand();
    private SequenceCommand sequenceCommand = new SequenceCommand();
    private ExamplesCommand examplesCommand = new ExamplesCommand();


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
        String chat_Id = update.getMessage().getChatId().toString();
        SendMessage message = new SendMessage();
        message.setChatId(chat_Id);
        if (UsersUsedTasks.get(chat_Id)==null) {
            UsersUsedTasks.put(chat_Id, new HashMap<TypeTask, ArrayList<String>>());
            UsersUsedTasks.get(chat_Id).put(TypeTask.Example, new ArrayList<>());
            UsersUsedTasks.get(chat_Id).put(TypeTask.Sequence, new ArrayList<>());
            UsersUsedTasks.get(chat_Id).put(TypeTask.Issue, new ArrayList<>());
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
                    var level = Level.get(chat_Id);
                    ExamplesCommand.GetLevel(level);
                    message.setText(examplesCommand.getTask(UsersUsedTasks.get(chat_Id).get(TypeTask.Example)));
                    UsersCondition.put(chat_Id, new Pair<>(State.Example, message.getText()));
                    break;
                case "/sequences":
                    message.setText(sequenceCommand.getTask(UsersUsedTasks.get(chat_Id).get(TypeTask.Sequence)));
                    UsersCondition.put(chat_Id, new Pair<>(State.Sequence, message.getText()));
                    break;
                case "/issue":
                    message.setText(issueCommand.getTask(UsersUsedTasks.get(chat_Id).get(TypeTask.Issue)));
                    UsersCondition.put(chat_Id, new Pair<>(State.Issue, message.getText()));
                    break;
                 case "/level":
                    message.setText(LevelCommand.getLevel(Level.get(chat_Id)));
                    UsersCondition.put(chat_Id, new Pair<>(State.Level, message.getText()));
                    break;
                case "/statistic":
                    message.setText(Statistic.getStatistic(UsersUsedTasks.get(chat_Id)));
                    break;
                default:
                    if (UsersCondition.get(chat_Id)!=null) {
                        Pair<State, String> condition = UsersCondition.get(chat_Id);
                        HashMap<TypeTask, ArrayList<String>> usedTasks = UsersUsedTasks.get(chat_Id);
                        System.out.println(usedTasks);
                        var answer = new Pair<String, Boolean>();
                        switch (condition.getFirst()) {
                            case Issue:
                                answer = issueCommand.getAnswer(condition.getSecond(), inputText);
                                ArrayList<String> usedIssues = usedTasks.get(TypeTask.Issue);
                                message.setText(answer.getFirst());
                                if (answer.getSecond()) {
                                    usedIssues.add(condition.getSecond());
                                    UsersUsedTasks.get(chat_Id).put(TypeTask.Issue, usedIssues);
                                    System.out.println(UsersUsedTasks);
                                }
                                break;
                            case Sequence:
                                answer = sequenceCommand.getAnswer(condition.getSecond(), inputText);
                                message.setText(answer.getFirst());
                                ArrayList<String> usedSequences = usedTasks.get(TypeTask.Sequence);
                                if (answer.getSecond()) {
                                    usedSequences.add(condition.getSecond());
                                    UsersUsedTasks.get(chat_Id).put(TypeTask.Sequence, usedSequences);
                                }
                                break;
                            case Example:
                                answer = examplesCommand.getAnswer(condition.getSecond(), inputText);
                                ArrayList<String> usedExamples = usedTasks.get(TypeTask.Example);
                                message.setText(answer.getFirst());
                                if (answer.getSecond()) {
                                    usedExamples.add(condition.getSecond());
                                    UsersUsedTasks.get(chat_Id).put(TypeTask.Example, usedExamples);
                                }
                                break;
                            case Level:
                                message.setText(LevelCommand.giveAnswer(inputText));
                                Level.put(chat_Id, inputText);
                                break;
                        }
                    }
                    else message.setText("Не понимаю тебя( \nЧтобы просмотреть список команд введи /help.");
                    System.out.println(UsersCondition);
                    System.out.println(UsersUsedTasks);
                    UsersCondition.put(chat_Id, new Pair<>(State.NoState, ""));
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
