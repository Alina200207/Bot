package main.java;

import main.java.commands.*;
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
                message.setReplyMarkup(getInlineClueButton());
            }
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
                    case "/start" -> {
                        message.setText(StartCommand.start());
                        message.setReplyMarkup(getReplyKeyboard());
                    }
                    case "/help", "Помощь" -> message.setText(HelpCommand.giveHelp());
                    case "/examples", "Пример" -> {
                        message.setText(getTask(userData, Type.TypeTask.Example));
                        userData.setCondition(State.Example, message.getText());
                    }
                    case "/sequences", "Последовательность" -> {
                        message.setText(getTask(userData, Type.TypeTask.Sequence));
                        message.setReplyMarkup(getInlineClueButton());
                        userData.setCondition(State.Sequence, message.getText());
                    }
                    case "/issue", "Загадка" -> {
                        message.setText(getTask(userData, Type.TypeTask.Issue));
                        message.setReplyMarkup(getInlineClueButton());
                        userData.setCondition(State.Issue, message.getText());
                    }
                    case "/level", "Уровень сложности примеров"-> {
                        message.setText(levelCommand.getLevel(userData.getLevel()));
                        userData.setCondition(State.Level, message.getText());
                    }
                    case "/statistic", "Статистика"-> {
                        var statistics = statistic.getCountTasks(userData.getUsedTasks());
                        var comparativeStatistic = statistic.getComparativeStatistic(UsersData, userData);
                        userData.setLastStatistic(statistics);
                        message.setText(statistic.getStatisticWithText(
                                statistics) + "\n\n" + statistic.getComparativeStatisticWithText(comparativeStatistic)
                        );
                    }
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
                            message.setReplyMarkup(getInlinesCommandButtons());
                        }
                        if (message.getText().equals(""))
                            message.setText("Не понимаю тебя( \nЧтобы просмотреть список команд введи /help.");
                        userData.setCondition(State.NoState, "");
                        System.out.println(userData.getLastStatistic().countAllTasks);
                    }
                }
                UsersData.put(chat_Id, userData);
            }
        }
        sendMessage(message);
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


    private void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println(message.getText());
        }
    }

    private ReplyKeyboard getInlineClueButton(){
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

    private ReplyKeyboard getInlinesCommandButtons(){
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttonsFirstRow = new ArrayList<>();
        List<InlineKeyboardButton> buttonsSecondRow = new ArrayList<>();
        List<InlineKeyboardButton> buttonsThirdRow = new ArrayList<>();
        InlineKeyboardButton sequenceButton = new InlineKeyboardButton();
        sequenceButton.setCallbackData("sequenceButton");
        sequenceButton.setText("Последовательность");
        buttonsFirstRow.add(sequenceButton);
        InlineKeyboardButton examplesButton = new InlineKeyboardButton();
        examplesButton.setCallbackData("examplesButton");
        examplesButton.setText("Пример");
        buttonsSecondRow.add(examplesButton);
        InlineKeyboardButton issueButton = new InlineKeyboardButton();
        issueButton.setCallbackData("issueButton");
        issueButton.setText("Загадка");
        buttonsThirdRow.add(issueButton);
        buttons.add(buttonsFirstRow);
        buttons.add(buttonsSecondRow);
        buttons.add(buttonsThirdRow);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(buttons);
        return markup;
    }
    private ReplyKeyboard getReplyKeyboard(){
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow buttonsFirstRow = new KeyboardRow();
        KeyboardRow buttonsSecondRow = new KeyboardRow();
        KeyboardRow buttonsThirdRow = new KeyboardRow();
        buttonsFirstRow.add(new KeyboardButton("Загадка"));
        buttonsFirstRow.add(new KeyboardButton("Пример"));
        buttonsFirstRow.add(new KeyboardButton("Последовательность"));
        buttonsSecondRow.add(new KeyboardButton("Статистика"));
        buttonsSecondRow.add(new KeyboardButton("Уровень сложности примеров"));
        buttonsThirdRow.add(new KeyboardButton("Игра на время"));
        buttonsThirdRow.add(new KeyboardButton("Помощь"));
        keyboard.add(buttonsFirstRow);
        keyboard.add(buttonsSecondRow);
        keyboard.add(buttonsThirdRow);
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);
        markup.setKeyboard(keyboard);
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