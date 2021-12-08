package Tests;

import main.java.processingTasks.MathTasks;
import main.java.processingTasks.Tasks;
import main.java.commands.TasksCommand;
import main.java.processingTasks.WorkingOnTask;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class TasksCommandTest {
    @Test
    void getAnswer_Возвращаетnullиfalse_КогдаПроисходитСменаВидаЗадач(){
        Tasks tasks = new Tasks(new MathTasks());
        TasksCommand taskCommand = new TasksCommand(new WorkingOnTask(tasks.GetExamplesLevel1()));
        var task = taskCommand.getTask(new ArrayList());
        var correctAnswer = tasks.GetExamplesLevel1().get(task);
        taskCommand.setTaskCommand(new WorkingOnTask(tasks.GetExamplesLevel2()));
        var result = taskCommand.getAnswer(task, correctAnswer);

        assertNull(result.answerString);
        assertEquals(false, result.correctness);
    }
}
