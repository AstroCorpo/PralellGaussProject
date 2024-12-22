import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Executioner implements Runnable {

    private ArrayList<ArrayList<Double>> M;
    private HashMap<ArrayList<Integer>, Double> factors;
    private Indivisible indivisible;

    Executioner(ArrayList<ArrayList<Double>> M, HashMap<ArrayList<Integer>, Double> factors, Indivisible indivisible) {
        this.M = M;
        this.factors = factors;
        this.indivisible = indivisible;
    }

    @Override
    public void run() {
        int j = indivisible.j;
        int i = indivisible.i;
        if(indivisible instanceof A) {
            factors.put(new ArrayList<>(List.of(j, i)), M.get(i).get(i) / M.get(j).get(i));
        } else if(indivisible instanceof B) {
            int k = ((B) indivisible).getK();
            Double temp = M.get(j).get(k);
            M.get(j).set(k, temp * factors.get(new ArrayList<>(List.of(j, i))));
        } else if(indivisible instanceof C) {
            int k = ((C) indivisible).getK();
            Double temp1 = M.get(j).get(k);
            Double temp2 = M.get(i).get(k);
            M.get(j).set(k, temp1 - temp2);
        }
    }
}
