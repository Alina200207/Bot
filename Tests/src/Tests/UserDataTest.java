package Tests;

import main.java.Bot;
import main.java.CountTasks;
import main.java.Type;
import main.java.UserData;
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

    @Test
    void setLastStatistic_ЗаписываетПоследнююСтатистикуВДанныеПользователя(){
        userData.setLastStatistic(new CountTasks(1, 1, 1));
        var lastStatistic = userData.getLastStatistic();

        assertEquals(3, lastStatistic.countAllTasks);
        assertEquals(1, lastStatistic.countExamples);
        assertEquals(1, lastStatistic.countIssues);
        assertEquals(1, lastStatistic.countSequences);
    }

    @Test
    void changeUsedTasksTest_ДобавляетИспользованнуюЗадачуВМассивИспользованныхЗадачПользователя(){
        var tasks = new ArrayList<String>();
        tasks.add("Tomorrow");

        userData.ChangeUsedTasks(Type.TypeTask.Issue, "Tomorrow");

        assertArrayEquals(tasks.toArray(), userData.getUsedTasks().get(Type.TypeTask.Issue).toArray());
    }

    @Test
    void changeCondition_ПереписываетСостояниеБотаИДанныеЗадачиДляПользователя(){
        userData.setCondition(Bot.State.Sequence, "1 2 3");

        assertEquals(Bot.State.Sequence, userData.getCondition().state);
        assertEquals("1 2 3", userData.getCondition().task);
    }
}
