package Main;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class Bot extends TelegramLongPollingBot {

    public enum State{
        NoState,
        Issue,
        Example,
        Sequence,
        Level
    };

    private Map<String, Condition> UsersCondition = new HashMap<>();
    private Map<String, HashMap<Type.TypeTask, ArrayList<String>>> UsersUsedTasks = new HashMap<>();
    private HashMap<String, String> Level = new HashMap<>();
    private IssueCommand issueCommand = new IssueCommand();
    private SequenceCommand sequenceCommand = new SequenceCommand();
    private ExamplesCommand examplesCommand = new ExamplesCommand();
    private LevelCommand levelCommand = new LevelCommand();
    private Statistic statistic = new Statistic();

    @Override
    public String getBotToken() {
        return "1983849094:AAHsoOtc0fJR05-KDdi14tqsuRdHrfAFpbs";
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
        if (UsersUsedTasks.get(chat_Id) == null) {
            UsersUsedTasks.put(chat_Id, new HashMap<>());
            UsersUsedTasks.get(chat_Id).put(Type.TypeTask.Example, new ArrayList<>());
            UsersUsedTasks.get(chat_Id).put(Type.TypeTask.Sequence, new ArrayList<>());
            UsersUsedTasks.get(chat_Id).put(Type.TypeTask.Issue, new ArrayList<>());
        }
        if(update.getMessage()!=null && update.getMessage().hasText()) {
            String inputText = update.getMessage().getText();
            var usedTask= UsersUsedTasks.get(chat_Id);
            switch (inputText) {
                case "/start" -> message.setText(StartCommand.start());
                case "/help" -> message.setText(HelpCommand.giveHelp());
                case "/examples" -> {
                    var level = Level.get(chat_Id);
                    examplesCommand.AssignLevel(level);
                    var usedExample = usedTask.get(Type.TypeTask.Example);
                    var example = examplesCommand.getTask(usedExample);
                    message.setText(example);
                    UsersCondition.put(chat_Id, new Condition(State.Example, message.getText()));
                }
                case "/sequences" -> {
                    var usedSequence = usedTask.get(Type.TypeTask.Sequence);
                    var sequence = sequenceCommand.getTask(usedSequence);
                    message.setText(sequence);
                    UsersCondition.put(chat_Id, new Condition(State.Sequence, message.getText()));
                }
                case "/issue" -> {
                    var usedIssue = usedTask.get(Type.TypeTask.Issue);
                    var issue = issueCommand.getTask(usedIssue);
                    message.setText(issue);
                    UsersCondition.put(chat_Id, new Condition(State.Issue, message.getText()));
                }
                case "/level" -> {
                    message.setText(levelCommand.getLevel(Level.get(chat_Id)));
                    UsersCondition.put(chat_Id, new Condition(State.Level, message.getText()));
                }
                case "/statistic" -> message.setText(statistic.getStatisticWithText(statistic.getCountTasks(UsersUsedTasks.get(chat_Id))));

                case "Подсказка" -> {
                    var condition = UsersCondition.get(chat_Id);
                    var clue = Clue.GetClues();
                    message.setText(clue.get(condition.task));
                }
                case "/time" -> {
                    var timer = new Timer();
                    //timer.schedule();
                    // вызовем статистику, в которой будет пред.рез и запишем его
                    // запускаем таймер
                    //когда таймер закончился, считаем новую статистику
                    // вычитаем из новой старую
                    //

                }
                default -> {
                    if (UsersCondition.get(chat_Id) != null) {
                        Condition condition = UsersCondition.get(chat_Id);
                        HashMap<Type.TypeTask, ArrayList<String>> usedTasks = UsersUsedTasks.get(chat_Id);
                        System.out.println(usedTasks);
                        var answer = new Answer("", false);
                        switch (condition.state) {
                            case Issue -> {
                                answer = issueCommand.getAnswer(condition.task, inputText);
                                ArrayList<String> usedIssues = usedTasks.get(Type.TypeTask.Issue);
                                message.setText(answer.answerString);
                                if (answer.correctness) {
                                    usedIssues.add(condition.task);
                                    UsersUsedTasks.get(chat_Id).put(Type.TypeTask.Issue, usedIssues);
                                    System.out.println(UsersUsedTasks);
                                }
                            }
                            case Sequence -> {
                                answer = sequenceCommand.getAnswer(condition.task, inputText);
                                message.setText(answer.answerString);
                                ArrayList<String> usedSequences = usedTasks.get(Type.TypeTask.Sequence);
                                if (answer.correctness) {
                                    usedSequences.add(condition.task);
                                    UsersUsedTasks.get(chat_Id).put(Type.TypeTask.Sequence, usedSequences);
                                }
                            }
                            case Example -> {
                                examplesCommand.AssignLevel(Level.get(chat_Id));
                                answer = examplesCommand.getAnswer(condition.task, inputText);
                                ArrayList<String> usedExamples = usedTasks.get(Type.TypeTask.Example);
                                message.setText(answer.answerString);
                                if (answer.correctness) {
                                    usedExamples.add(condition.task);
                                    UsersUsedTasks.get(chat_Id).put(Type.TypeTask.Example, usedExamples);
                                }
                            }
                            case Level -> {
                                message.setText(levelCommand.getAnswer(inputText));
                                Level.put(chat_Id, inputText);
                            }
                        }
                    } else message.setText("Не понимаю тебя( \nЧтобы просмотреть список команд введи /help.");
                    System.out.println(UsersCondition);
                    System.out.println(UsersUsedTasks);
                    UsersCondition.put(chat_Id, new Condition(State.NoState, ""));
                }
            }
        }
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}