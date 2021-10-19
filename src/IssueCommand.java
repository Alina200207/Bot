public class IssueCommand {

    private static Issue issue = new Issue(Tasks.Issues);
    public static String getIssue(){
        return issue.GetIssue();
    }

    public static String giveAnswer(String condition, String playerAnswer){
        var result = issue.CompareResult(condition, playerAnswer);
        var str = "Сыграем еще? Выбирай команду:\n" +
                "/issue \n" +
                "/examples \n" +
                "/sequences";
        var message = "";
        if (result.getFirst())
            message = "Верно! \n" + str;
        else message = "Неверно :( \n" +
                String.format("Правильный ответ: %s \n", result.getSecond()) + str;
        return message;
    }
}
