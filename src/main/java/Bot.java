package main.java;

import main.java.commands.*;
import main.java.clues.Clue;
import main.java.clues.GetClue;
import main.java.statistic.Statistic;
import main.java.userData.UserData;
import main.java.structures.AnswerAndCorrectness;
import main.java.structures.Condition;
import main.java.structures.Type;
import org.glassfish.grizzly.utils.Pair;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
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
            var resultOfProcessing = processingCallbackQuery(update.getCallbackQuery());
            message.setText(resultOfProcessing.getFirst());
            message.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
            if (resultOfProcessing.getSecond()){
                message.setReplyMarkup(inlineClueButton());
            }
            sendMessage(message);
        }
        else {
            String chat_Id = update.getMessage().getChatId().toString();
            message.setChatId(chat_Id);
            if (UsersData.get(chat_Id) == null) {
                UsersData.put(chat_Id, new UserData(chat_Id));
            }
            UserData userData = UsersData.get(chat_Id);
            if (update.getMessage() != null && update.getMessage().hasText()) {
                String inputText = update.getMessage().getText();
                switch (inputText) {
                    case "/start", "/help", "Помощь", "/time", "Игра на время", "/examples", "Пример", "/sequences", "Последовательность",
                            "/issue", "Загадка", "/level", "Уровень сложности примеров",
                            "/statistic", "Статистика" ->
                            {
                                processingCommands(userData, inputText, chat_Id);
                            }
                    default -> {
                        if (userData.getCondition() != null) {
                            message.setText(processingAnswer(userData, inputText));
                            message.setReplyMarkup(inlineKeyboard());
                        }
                        if (message.getText().equals(""))
                            message.setText("Не понимаю тебя( \nЧтобы просмотреть список команд введи /help.");
                        userData.setCondition(State.NoState, "");
                        UsersData.put(chat_Id, userData);
                        sendMessage(message);
                    }
                }
            }
        }
    }

    private String getTask(UserData userData, Type.TypeTask type) {
        switch (type){
            case Example -> {
                var level = userData.getLevel();
                examplesCommand.AssignLevel(level);
                var usedExample = userData.getUsedTasks().get(Type.TypeTask.Example);
                return examplesCommand.getTask(usedExample);
            }
            case Sequence -> {
                var usedSequence = userData.getUsedTasks().get(Type.TypeTask.Sequence);
                return sequenceCommand.getTask(usedSequence);
            }
            case Issue -> {
                var usedIssue = userData.getUsedTasks().get(Type.TypeTask.Issue);
                return issueCommand.getTask(usedIssue);
            }
        }
        return "";
    }

    private String processingAnswer(UserData userData, String inputText) {
        Condition condition = userData.getCondition();
        var answer = new AnswerAndCorrectness("", false);
        var message = "";
        switch (condition.state) {
            case Issue -> {
                answer = issueCommand.getAnswerWithText(issueCommand.getAnswer(condition.task, inputText));
                if (answer.correctness) {
                    userData.ChangeUsedTasks(Type.TypeTask.Issue, condition.task);
                    var statistics = statistic.getCountTasks(userData.getUsedTasks());
                    userData.setLastStatistic(statistics);
                }
                message = answer.answerString;
            }
            case Sequence -> {
                answer = sequenceCommand.getAnswerWithText(sequenceCommand.getAnswer(condition.task, inputText));
                if (answer.correctness) {
                    userData.ChangeUsedTasks(Type.TypeTask.Sequence, condition.task);
                    var statistics = statistic.getCountTasks(userData.getUsedTasks());
                    userData.setLastStatistic(statistics);
                }
                message = answer.answerString;
            }
            case Example -> {
                examplesCommand.AssignLevel(userData.getLevel());
                answer = examplesCommand.getAnswerWithText(examplesCommand.getAnswer(condition.task, inputText));
                if (answer.correctness) {
                    userData.ChangeUsedTasks(Type.TypeTask.Example, condition.task);
                    var statistics = statistic.getCountTasks(userData.getUsedTasks());
                    userData.setLastStatistic(statistics);
                }
                message = answer.answerString;
            }
            case Level -> {
                userData.setLevel(inputText);
                message = levelCommand.getAnswer(inputText);
            }
            default -> {
                message = "";
            }
        }
        UsersData.put(userData.getId(), userData);
        return message;
    }

    private void processingCommands(UserData userData, String inputText, String chat_id) {
        SendMessage message = new SendMessage();
        message.setChatId(chat_id);
        var needKeyboard = false;
        switch (inputText) {
            case "/start" -> {
                message.setText(StartCommand.start());
                message.setReplyMarkup(replyKeyboard());
            }
            case "/help", "Помощь" -> message.setText(HelpCommand.giveHelp());
            case "/time", "Игра на время" -> {
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
            case "/examples", "Пример" -> {
                message.setText(getTask(userData, Type.TypeTask.Example));
                userData.setCondition(State.Example, message.getText());
            }
            case "/sequences", "Последовательность" -> {
                message.setText(getTask(userData, Type.TypeTask.Sequence));
                needKeyboard = true;
                userData.setCondition(State.Sequence, message.getText());
            }
            case "/issue", "Загадка" -> {
                message.setText(getTask(userData, Type.TypeTask.Issue));
                needKeyboard = true;
                userData.setCondition(State.Issue, message.getText());
            }
            case "/level", "Уровень сложности примеров" -> {
                message.setText(levelCommand.getLevel(userData.getLevel()));
                userData.setCondition(State.Level, message.getText());
            }
            case "/statistic", "Статистика" -> {
                var statistics = statistic.getCountTasks(userData.getUsedTasks());
                var comparativeStatistic = statistic.getComparativeStatistic(UsersData, userData);
                userData.setLastStatistic(statistics);
                message.setText(statistic.getStatisticWithText(
                        statistics) + "\n\n" + statistic.getComparativeStatisticWithText(comparativeStatistic));
            }
        }
        if (needKeyboard){
            message.setReplyMarkup(inlineClueButton());
        }
        UsersData.put(userData.getId(), userData);
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

    private ReplyKeyboard inlineClueButton(){
        InlineKeyboardButton clueButton = new InlineKeyboardButton();
        clueButton.setCallbackData("clueButton");
        clueButton.setText("Подсказка");
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(List.of(List.of(clueButton)));
        return markup;
    }

    private ReplyKeyboard inlineKeyboard(){
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<String> buttonsText = Arrays.asList("Последовательность", "Пример", "Загадка");
        List<String> buttonsCallbackData = Arrays.asList("sequenceButton", "examplesButton", "issueButton");
        for (int i = 0; i < 3; i++) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setCallbackData(buttonsCallbackData.get(i));
            button.setText(buttonsText.get(i));
            buttons.add(List.of(button));
        }
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(buttons);
        return markup;
    }
    private ReplyKeyboard replyKeyboard(){
        KeyboardRow buttonsFirstRow = (KeyboardRow)Arrays.asList(new KeyboardButton("Загадка"),
                new KeyboardButton("Пример"), new KeyboardButton("Последовательность"));
        KeyboardRow buttonsSecondRow = (KeyboardRow)Arrays.asList(new KeyboardButton("Статистика"),
                new KeyboardButton("Уровень сложности примеров"));
        KeyboardRow buttonsThirdRow = (KeyboardRow)Arrays.asList(new KeyboardButton("Игра на время"),
                new KeyboardButton("Помощь"));
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);
        markup.setKeyboard(Arrays.asList(buttonsFirstRow, buttonsSecondRow, buttonsThirdRow));
        return markup;
    }

    private Pair<String, Boolean> processingCallbackQuery(CallbackQuery button){
        String message = "";
        String chat_id = button.getMessage().getChatId().toString();
        System.out.println(chat_id);
        String data = button.getData();
        var needClueButton = false;
        var userData = UsersData.get(chat_id);
        switch (data) {
            case "clueButton" -> {
                var condition = UsersData.get(chat_id).getCondition();
                var clue = Clue.GetClues();
                message = clue.get(condition.task);
            }
            case "sequenceButton" -> {
                message = getTask(userData, Type.TypeTask.Sequence);
                userData.setCondition(State.Sequence, message);
                needClueButton = true;
            }
            case "examplesButton" -> {
                message = getTask(userData, Type.TypeTask.Example);
                userData.setCondition(State.Example, message);
            }
            case "issueButton" -> {
                message = getTask(userData, Type.TypeTask.Issue);
                userData.setCondition(State.Issue, message);
                needClueButton = true;
            }
        }
        UsersData.put(chat_id, userData);
        return new Pair<>(message, needClueButton);
    }
}