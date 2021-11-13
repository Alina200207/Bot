package Main;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.*;

public class Bot extends TelegramLongPollingBot {

    public enum State{
        NoState,
        Issue,
        Example,
        Sequence,
        Level
    };

    private Map<String, UserData> UsersData = new HashMap<>();
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
        if (UsersData.get(chat_Id) == null){
            UsersData.put(chat_Id, new UserData(chat_Id));
        }
        UserData userData = UsersData.get(chat_Id);
        if(update.getMessage()!=null && update.getMessage().hasText()) {
            String inputText = update.getMessage().getText();
            var usedTask = userData.GetUsedTasks();
            switch (inputText) {
                case "/start" -> message.setText(StartCommand.start());
                case "/help" -> message.setText(HelpCommand.giveHelp());
                case "/examples" -> {
                    var level = userData.GetLevel();
                    examplesCommand.AssignLevel(level);
                    var usedExample = usedTask.get(Type.TypeTask.Example);
                    var example = examplesCommand.getTask(usedExample);
                    message.setText(example);
                    userData.SetCondition(State.Example, message.getText());
                }
                case "/sequences" -> {
                    var usedSequence = usedTask.get(Type.TypeTask.Sequence);
                    var sequence = sequenceCommand.getTask(usedSequence);
                    message.setText(sequence);
                    userData.SetCondition(State.Sequence, message.getText());
                }
                case "/issue" -> {
                    var usedIssue = usedTask.get(Type.TypeTask.Issue);
                    var issue = issueCommand.getTask(usedIssue);
                    message.setText(issue);
                    userData.SetCondition(State.Issue, message.getText());
                }
                case "/level" -> {
                    message.setText(levelCommand.getLevel(userData.GetLevel()));
                    userData.SetCondition(State.Level, message.getText());
                }
                case "/statistic" -> message.setText(statistic.getStatisticWithText(statistic.getCountTasks(userData.GetUsedTasks())));

                case "Подсказка" -> {
                    var condition = userData.GetCondition();
                    var clue = Clue.GetClues();
                    message.setText(clue.get(condition.task));
                }
                case "/time" -> {
                    var statisticBeforeTime = statistic.getCountTasks(userData.GetUsedTasks());
                    Timer time = new Timer();
                    time.schedule(new TimerTask() {
                        int i = 0;
                        @Override
                        public void run() {
                            if(i>=2){
                                var statisticAfterTime = statistic.getCountTasks(userData.GetUsedTasks());
                                message.setText("Время вышло. " + statistic.getStatisticWithText(
                                        statistic.getCountTasks(statisticBeforeTime, statisticAfterTime)));
                                try {
                                    execute(message);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                                time.cancel();
                                return;
                            }
                            message.setText("прошло " + (30+i*30) + " сек");
                            try {
                                execute(message);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                            i = i + 1;
                        }
                    }, 30000, 30000); //(4000 - ПОДОЖДАТЬ ПЕРЕД НАЧАЛОМ В МИЛИСЕК, ПОВТОРЯТСЯ 4 СЕКУНДЫ (1 СЕК = 1000 МИЛИСЕК))
                    message.setText("Время пошло. У вас есть 90 секунд");
                }
                default -> {
                    if (userData.GetCondition() != null) {
                        Condition condition = userData.GetCondition();
                        condition = userData.GetCondition();
                        var answer = new Answer("", false);
                        switch (condition.state) {
                            case Issue -> {
                                answer = issueCommand.getAnswer(condition.task, inputText);
                                message.setText(answer.answerString);
                                if (answer.correctness) {
                                    userData.ChangeUsedTasks(Type.TypeTask.Issue, condition.task);
                                }
                            }
                            case Sequence -> {
                                answer = sequenceCommand.getAnswer(condition.task, inputText);
                                message.setText(answer.answerString);
                                if (answer.correctness) {
                                    userData.ChangeUsedTasks(Type.TypeTask.Sequence, condition.task);
                                }
                            }
                            case Example -> {
                                examplesCommand.AssignLevel(userData.GetLevel());
                                answer = examplesCommand.getAnswer(condition.task, inputText);
                                message.setText(answer.answerString);
                                if (answer.correctness) {
                                    userData.ChangeUsedTasks(Type.TypeTask.Example, condition.task);
                                }
                            }
                            case Level -> {
                                message.setText(levelCommand.getAnswer(inputText));
                                userData.SetLevel(inputText);
                            }
                        }
                    }
                    else message.setText("Не понимаю тебя( \nЧтобы просмотреть список команд введи /help.");
                    userData.SetCondition(State.NoState, "");
                    UsersData.put(chat_Id, userData);
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