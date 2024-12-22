import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        String loadPath = "src/main/resources/examples/matrix.txt";
        String savePath = "src/main/resources/results/matrix.txt";
        String saveDotPath = "src/main/resources/dot/graph.dot";
        Boolean timer = true;
        Boolean trim = true;

        ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
        Integer n = CustomReader.readMatrix(matrix, loadPath);;

        ArrayList<ArrayList<Indivisible>> foaty = new ArrayList<>();
        HashMap<ArrayList<Integer>, Double> factors = new HashMap<>();

        HashSet<ArrayList<Indivisible>> dependency = Dependency.generateDependency(n, timer);
        ArrayList<Indivisible> word = Word.generateWord(n, timer);
        ArrayList<Integer> word_int = Word.generateWordInt(word, timer);

        ArrayList<Edge> edges = EdgeGraph.generateEdgesFromDependency(dependency, word);
        EdgeGraph graph = new EdgeGraph(word_int, edges);

        if(trim) {
            graph.trim(timer);
        }

        CustomWriter.writeGraphToDot(graph, word, saveDotPath);

        Foaty.getFoaty(foaty, graph, word, timer);

        ParallelGauss.executeGauss(matrix, foaty, factors, timer);

        CustomWriter.writeMatrix(matrix, savePath);

    }
}