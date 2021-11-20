package Tests;

import main.java.PercentStatistic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PercentStatisticTest {
    static PercentStatistic percentStat;
    @BeforeAll
    static void setUp(){
        percentStat = new PercentStatistic(20, 20, 4);
    }
    @Test
    void initializationPercentStatistic(){
        assertEquals(20, percentStat.percentFewer);
        assertEquals(20, percentStat.percentSame);
        assertEquals(4, percentStat.countUsers);
    }
}
