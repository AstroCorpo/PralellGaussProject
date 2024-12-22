import java.util.ArrayList;
import java.util.logging.Logger;

import static java.lang.System.currentTimeMillis;

public class Foaty {

    private static final Logger logger = Logger.getLogger(Foaty.class.getName());

    Foaty() {

    }

    public static void getFoaty(ArrayList<ArrayList<Indivisible>> foaty, EdgeGraph graph, ArrayList<Indivisible> word, Boolean timer) {

        long startTime = currentTimeMillis();

        ArrayList<ArrayList<Integer>> groups = graph.group_nodes_by_distance_from_roots();
        ArrayList<Indivisible> temp;
        for(ArrayList<Integer> group : groups) {
            temp = new ArrayList<>();
            for(Integer index : group) {
                temp.add(word.get(index-1));
            }
            foaty.add(temp);
        }

        if(timer) {
            logger.info("Duration of calculating Foaty: " + (currentTimeMillis() - startTime));
        }
    }

}
