package Main;

public abstract class TasksCommand {
    public WorkingOnTask workingOnTask;

    public void getTaskCommand(WorkingOnTask task) {
        workingOnTask = task;
    }
    public Answer getAnswer(String condition, String playerAnswer){
        var result = workingOnTask.compareResult(condition, playerAnswer);
        var str = "Сыграем еще? Выбирай команду:\n" +
                "/issue \n" +
                "/examples \n" +
                "/sequences";
        var message = "";
        if (result.correctness)
            message = "Верно! \n" + str;
        else message = "Неверно :( \n" +
                String.format("Правильный ответ: %s \n", result.answerString) + str;
        return new Answer(message, result.correctness);
    }
}
