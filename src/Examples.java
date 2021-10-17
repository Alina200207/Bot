import org.glassfish.grizzly.utils.Pair;

import java.util.ArrayList;
import java.util.Random;

public class Examples {
    private ArrayList<Pair<String, String>> Examples;
    public Examples() {
        Examples = new ArrayList<Pair<String, String>>(){};
        Examples.add(new Pair<>("34+17+90", "141"));
        Examples.add(new Pair<>("23*2+18", "64"));
        Examples.add(new Pair<>("478+23-45)", "456"));
        Examples.add(new Pair<>("567-896", "-329"));
        Examples.add(new Pair<>("3*12*4", "144"));
        Examples.add(new Pair<>("4958-78", "4880"));
        Examples.add(new Pair<>("121/11", "11"));
        Examples.add(new Pair<>("753+33+5-20", "771"));
        Examples.add(new Pair<>("238*3", "714"));
    }

}
