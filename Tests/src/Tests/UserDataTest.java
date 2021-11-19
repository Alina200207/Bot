package Tests;

import Main.Bot;
import Main.CountTasks;
import Main.Type;
import Main.UserData;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDataTest {
    static UserData userData;
    @BeforeAll
    static void setUp(){
        userData = new UserData("111");
    }
    @Order(1)
    @Test
    void initializingUserDataTest(){
        assertEquals("1", userData.getLevel());
        assertEquals("111", userData.getId());
        assertEquals(Bot.State.NoState, userData.getCondition().state);
        assertEquals("", userData.getCondition().task);
        assertEquals(0, userData.getLastStatistic().countAllTasks);
        assertNotNull(userData.getUsedTasks());
    }

    @Test
    void changeLastStatisticTest(){
        userData.setLastStatistic(new CountTasks(1, 1, 1));
        assertEquals(3, userData.getLastStatistic().countAllTasks);
        assertEquals(1, userData.getLastStatistic().countExamples);
        assertEquals(1, userData.getLastStatistic().countIssues);
        assertEquals(1, userData.getLastStatistic().countSequences);
    }

    @Test
    void changeUsedTasksTest(){
        userData.ChangeUsedTasks(Type.TypeTask.Issue, "Tomorrow");
        var tasks = new ArrayList<String>();
        tasks.add("Tomorrow");
        assertArrayEquals(tasks.toArray(), userData.getUsedTasks().get(Type.TypeTask.Issue).toArray());
    }

    @Test
    void changeConditionTest(){
        userData.setCondition(Bot.State.Sequence, "1 2 3");
        assertEquals(Bot.State.Sequence, userData.getCondition().state);
        assertEquals("1 2 3", userData.getCondition().task);
    }
}
