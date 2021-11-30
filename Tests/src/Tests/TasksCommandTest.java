package Tests;

import main.java.Tasks;
import main.java.TasksCommand;
import main.java.WorkingOnTask;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class TasksCommandTest {
    @Test
    void getAnswer_Возвращаетnullиfalse_КогдаПроисходитСменаВидаЗадач(){
        TasksCommand taskCommand = new TasksCommand(new WorkingOnTask(Tasks.GetExamplesLevel1()));
        var task = taskCommand.getTask(new ArrayList());
        var correctAnswer = Tasks.GetExamplesLevel1().get(task);
        taskCommand.setTaskCommand(new WorkingOnTask(Tasks.GetExamplesLevel2()));
        var result = taskCommand.getAnswer(task, correctAnswer);

        assertNull(result.answerString);
        assertEquals(false, result.correctness);
    }
}
