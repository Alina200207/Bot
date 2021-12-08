package Tests;

import main.java.processingTasks.Tasks;
import main.java.processingTasks.WorkingOnTask;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkingOnTaskTest {


    @Test
    void Example_GetTask_ВернетНеиспользованнуюЗадачу_КогдаОнаОднаИспользованаИОднаНеИспользована(){
        var Examples = new HashMap<String, String>();
        Examples.put("80+3", "83");
        var examplesClass = new WorkingOnTask(Examples);
        var UsedTasks = new ArrayList<String>();
        UsedTasks.add("5*10");

        var result = examplesClass.getTask(UsedTasks);

        assertEquals("80+3", result);
    }

    @Test
    void compareResult_ПроверитПравильностьОтветаПользователя_КогдаОтветПравильныйИНеправильный() {
        var userTask = Tasks.GetIssues();
        var issue = userTask.keySet().toArray()[0].toString();
        var Examples = new HashMap<String, String>();
        var Issues = new HashMap<String, String>();
        var examplesClass = new WorkingOnTask(Examples);
        Examples.put("80+3", "83");
        Issues.put(userTask.get(issue), issue);
        var issueClass = new WorkingOnTask(Issues);

        var falseResult = examplesClass.compareResult("80+3", "3").correctness;
        var trueResult = issueClass.compareResult(userTask.get(issue), issue).correctness;
        var result = examplesClass.compareResult("80+3", "83").correctness;

        assertEquals(false, falseResult);
        assertEquals(true, trueResult);
        assertTrue(result);
    }
}