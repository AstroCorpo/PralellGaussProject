import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class ParallelGauss {

    private static final Logger logger = Logger.getLogger(ParallelGauss.class.getName());

    ParallelGauss() {

    }

    public static void executeGauss(ArrayList<ArrayList<Double>> matrix, ArrayList<ArrayList<Indivisible>> foaty, HashMap<ArrayList<Integer>, Double> factors, Boolean timer) {

        long startTime = System.currentTimeMillis();

        for(ArrayList<Indivisible> level : foaty) {
            ExecutorService executorService = Executors.newFixedThreadPool(level.size());
            for(Indivisible indv : level) {
                Executioner executioner = new Executioner(matrix, factors, indv);
                executorService.submit(executioner);
            }

            executorService.shutdown();

            while (!executorService.isTerminated()) {}

        }

        if(timer) {
            logger.info("Duration of executing Gauss: " + (System.currentTimeMillis() - startTime));
        }

    }
}
