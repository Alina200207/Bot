package main.java;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
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
    private final IssueCommand issueCommand = new IssueCommand();
    private final SequenceCommand sequenceCommand = new SequenceCommand();
    private final ExamplesCommand examplesCommand = new ExamplesCommand();
    private final LevelCommand levelCommand = new LevelCommand();
    private final Statistic statistic = new Statistic();
    private final GetClue clues = new GetClue();

    @Override
    public String getBotToken() {
        return "1983849094:AAGWyKDIO9jovVRn5BwVdKWSEfxo_qEw384";
    }

    @Override
    public String getBotUsername() {
        return "@Pump_up_your_brain_bot";
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        if (update.hasCallbackQuery()){
            message.setText(processingCallbackQuery(update.getCallbackQuery()));
            message.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
        }
        else {
            String chat_Id = update.getMessage().getChatId().toString();
            System.out.println(chat_Id);
            message.setChatId(chat_Id);
            if (UsersData.get(chat_Id) == null) {
                UsersData.put(chat_Id, new UserData(chat_Id));
            }
            UserData userData = UsersData.get(chat_Id);
            if (update.getMessage() != null && update.getMessage().hasText()) {
                String inputText = update.getMessage().getText();
                var usedTask = userData.getUsedTasks();
                switch (inputText) {
                    case "/start" -> message.setText(StartCommand.start());
                    case "/help" -> message.setText(HelpCommand.giveHelp());
                    case "/examples" -> {
                        var level = userData.getLevel();
                        examplesCommand.AssignLevel(level);
                        var usedExample = usedTask.get(Type.TypeTask.Example);
                        var example = examplesCommand.getTask(usedExample);
                        message.setText(example);
                        userData.setCondition(State.Example, message.getText());
                    }
                    case "/sequences" -> {
                        var usedSequence = usedTask.get(Type.TypeTask.Sequence);
                        var sequence = sequenceCommand.getTask(usedSequence);
                        message.setText(sequence);
                        message.setReplyMarkup(getInlineButton());
                        userData.setCondition(State.Sequence, message.getText());
                    }
                    case "/issue" -> {
                        var usedIssue = usedTask.get(Type.TypeTask.Issue);
                        var issue = issueCommand.getTask(usedIssue);
                        message.setText(issue);
                        message.setReplyMarkup(getInlineButton());
                        userData.setCondition(State.Issue, message.getText());
                    }
                    case "/level" -> {
                        message.setText(levelCommand.getLevel(userData.getLevel()));
                        userData.setCondition(State.Level, message.getText());
                    }
                    case "/statistic" -> {
                        var statistics = statistic.getCountTasks(userData.getUsedTasks());
                        var comparativeStatistic = statistic.getComparativeStatistic(UsersData, userData);
                        userData.setLastStatistic(statistics);
                        message.setText(statistic.getStatisticWithText(
                                statistics) + "\n\n" + statistic.getComparativeStatisticWithText(comparativeStatistic)
                        );
                    }
                    case "clueButton", "Подсказка" -> {
                        var clue = clues.getClue(userData.getCondition().task);
                        if (clue != null) {
                            message.setText(clue);
                        } else
                            message.setText("Подсказки даются только после выбора загадок или последовательностей. ");
                    }
                    case "/time" -> {
                        var statisticBeforeTime = statistic.getCountTasks(userData.getUsedTasks());
                        Timer time = new Timer();
                        time.schedule(new TimerTask() {
                            int i = 0;

                            @Override
                            public void run() {
                                if (i >= 2) {
                                    var statisticAfterTime = statistic.getCountTasks(userData.getUsedTasks());
                                    message.setText("Время вышло. " + statistic.getStatisticWithText(
                                            statistic.getCountTasks(statisticBeforeTime, statisticAfterTime)));
                                    sendMessage(message);
                                    time.cancel();
                                    return;
                                }
                                message.setText("прошло " + (30 + i * 30) + " сек");
                                sendMessage(message);
                                i = i + 1;
                            }
                        }, 30000, 30000);
                        message.setText("Время пошло. У тебя есть 90 секунд. Выбирай команду!");
                    }
                    default -> {
                        if (userData.getCondition() != null) {
                            Condition condition = userData.getCondition();
                            var answer = new Answer("", false);
                            switch (condition.state) {
                                case Issue -> {
                                    answer = issueCommand.getAnswerWithText(issueCommand.getAnswer(condition.task, inputText));
                                    message.setText(answer.answerString);
                                    if (answer.correctness) {
                                        userData.ChangeUsedTasks(Type.TypeTask.Issue, condition.task);
                                        var statistics = statistic.getCountTasks(userData.getUsedTasks());
                                        userData.setLastStatistic(statistics);
                                    }
                                }
                                case Sequence -> {
                                    answer = sequenceCommand.getAnswerWithText(sequenceCommand.getAnswer(condition.task, inputText));
                                    message.setText(answer.answerString);
                                    if (answer.correctness) {
                                        userData.ChangeUsedTasks(Type.TypeTask.Sequence, condition.task);
                                        var statistics = statistic.getCountTasks(userData.getUsedTasks());
                                        userData.setLastStatistic(statistics);
                                    }
                                }
                                case Example -> {
                                    examplesCommand.AssignLevel(userData.getLevel());
                                    answer = examplesCommand.getAnswerWithText(examplesCommand.getAnswer(condition.task, inputText));
                                    message.setText(answer.answerString);
                                    if (answer.correctness) {
                                        userData.ChangeUsedTasks(Type.TypeTask.Example, condition.task);
                                        var statistics = statistic.getCountTasks(userData.getUsedTasks());
                                        userData.setLastStatistic(statistics);
                                    }
                                }
                                case Level -> {
                                    message.setText(levelCommand.getAnswer(inputText));
                                    userData.setLevel(inputText);
                                }
                            }
                        } else message.setText("Не понимаю тебя( \nЧтобы просмотреть список команд введи /help.");
                        userData.setCondition(State.NoState, "");
                        System.out.println(userData.getLastStatistic().countAllTasks);
                        UsersData.put(chat_Id, userData);
                    }
                }
            }
        }
        sendMessage(message);
    }


    private void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println(message.getText());
        }
    }

    private ReplyKeyboard getInlineButton(){
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttonsFirst = new ArrayList<>();
        InlineKeyboardButton clueButton = new InlineKeyboardButton();
        clueButton.setCallbackData("clueButton");
        clueButton.setText("Подсказка");
        buttonsFirst.add(clueButton);
        buttons.add(buttonsFirst);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(buttons);
        return markup;
    }

    private String processingCallbackQuery(CallbackQuery button){
        String message = "Подсказки нет.";
        String chat_id = button.getMessage().getChatId().toString();
        if (button.getData().equals("clueButton")){
            var condition = UsersData.get(chat_id).getCondition();
            var clue = Clue.GetClues();
            message = clue.get(condition.task);
        }
        System.out.println(message);
        return message;
    }
}