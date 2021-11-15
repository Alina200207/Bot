package Main;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
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
        String chat_Id = update.getMessage().getChatId().toString();
        SendMessage message = new SendMessage();
        message.setChatId(chat_Id);
        System.out.println(update);
        message.setText("ji");

        if (UsersData.get(chat_Id) == null){
            UsersData.put(chat_Id, new UserData(chat_Id));
        }
        UserData userData = UsersData.get(chat_Id);
        if (update.hasCallbackQuery()){
            System.out.println("fghhgfvbhjg");
            message.setText(processingCallbackQuery(update.getCallbackQuery()));
        }
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
                    message.setReplyMarkup(getInlineButton());
                    userData.SetCondition(State.Sequence, message.getText());
                }
                case "/issue" -> {
                    var usedIssue = usedTask.get(Type.TypeTask.Issue);
                    var issue = issueCommand.getTask(usedIssue);
                    message.setText(issue);
                    message.setReplyMarkup(getInlineButton());
                    userData.SetCondition(State.Issue, message.getText());
                }
                case "/level" -> {
                    message.setText(levelCommand.getLevel(userData.GetLevel()));
                    userData.SetCondition(State.Level, message.getText());
                }
                case "/statistic" -> {
                    var statistics = statistic.getCountTasks(userData.GetUsedTasks());
                    userData.SetLastStatistic(statistics);
                    message.setText(statistic.getStatisticWithText(
                            statistics) + "\n\n" +
                            statistic.getComparativeStatistic(UsersData, userData));
                }

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
                                sendMessage(message);
                                time.cancel();
                                return;
                            }
                            message.setText("прошло " + (30+i*30) + " сек");
                            sendMessage(message);
                            i = i + 1;
                        }
                    }, 30000, 30000);
                    message.setText("Время пошло. У тебя есть 90 секунд. Выбирай команду!");
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
                                    var statistics = statistic.getCountTasks(userData.GetUsedTasks());
                                    userData.SetLastStatistic(statistics);
                                }
                            }
                            case Sequence -> {
                                answer = sequenceCommand.getAnswer(condition.task, inputText);
                                message.setText(answer.answerString);
                                if (answer.correctness) {
                                    userData.ChangeUsedTasks(Type.TypeTask.Sequence, condition.task);
                                    var statistics = statistic.getCountTasks(userData.GetUsedTasks());
                                    userData.SetLastStatistic(statistics);
                                }
                            }
                            case Example -> {
                                examplesCommand.AssignLevel(userData.GetLevel());
                                answer = examplesCommand.getAnswer(condition.task, inputText);
                                message.setText(answer.answerString);
                                if (answer.correctness) {
                                    userData.ChangeUsedTasks(Type.TypeTask.Example, condition.task);
                                    var statistics = statistic.getCountTasks(userData.GetUsedTasks());
                                    userData.SetLastStatistic(statistics);
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
                    System.out.println(userData.GetLastStatistic().countAllTasks);
                    UsersData.put(chat_Id, userData);
                }
            }
        }
        else if (update.hasCallbackQuery()){
            System.out.println("dfghjkl");
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
        clueButton.setText("Подсказка");
        clueButton.setCallbackData("clueButton");
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
            var condition = UsersData.get(chat_id).GetCondition();
            var clue = Clue.GetClues();
            message = clue.get(condition.task);
        }
        System.out.println(message);
        return message;
    }
    public static InlineKeyboardMarkup sendInlineKeyBoardMessage(String chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Тык");
        inlineKeyboardButton1.setCallbackData("Button \"Тык\" has been pressed");
        inlineKeyboardButton2.setText("Тык2");
        inlineKeyboardButton2.setCallbackData("Button \"Тык2\" has been pressed");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(new InlineKeyboardButton());
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}