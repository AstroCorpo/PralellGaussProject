import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class EdgeGraph {

    private static final Logger logger = Logger.getLogger(EdgeGraph.class.getName());

    private ArrayList<Integer> nodes;

    private ArrayList<Edge> edges;

    private ArrayList<Integer> roots;

    EdgeGraph(ArrayList<Integer> nodes, ArrayList<Edge> edges) {

        this.nodes = nodes;
        this.edges = edges;

        ArrayList<Integer> temp = new ArrayList<>(nodes);
        for(Edge edge : edges) {
            temp.remove((Integer)edge.getTarget());
        }

        this.roots = temp;

    }

    public ArrayList<ArrayList<Integer>> group_nodes_by_distance_from_roots() {

        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();

        temp.add(new ArrayList<>(roots));

        for(int i = 1; i < edges.size() + 1; i++) {

            ArrayList<Integer> group = new ArrayList<>();

            for(Edge edge : edges) {
                if(temp.get(i - 1).contains(edge.getSource())) {
                    for(ArrayList<Integer> temp_group : temp) {
                        if (temp_group.contains(edge.getTarget())) {
                            temp_group.remove((Integer) edge.getTarget());
                        }
                    }
                    if(!group.contains(edge.getTarget())) {
                        group.add(edge.getTarget());
                    }
                }
            }

            if (!group.isEmpty()) {
                temp.add(group);
            } else {
                break;
            }

        }

        return temp;

    }

    public void remove_edge(int source, int target) {
        for(Edge edge : edges) {
            if(edge.getSource() == source && edge.getTarget() == target) {
                edges.remove((Edge) edge);
                break;
            }
        }
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    public void trim(Boolean timer) {

        long startTime = System.currentTimeMillis();

        for(Integer node1 : nodes) {
            for(Integer node2 : nodes) {
                if(node1.equals(node2)) {
                    continue;
                }
                if(isPathBetween(node1, node2)) {
                    remove_edge(node1, node2);
                    if(!isPathBetween(node1, node2)) {
                        edges.add(new Edge(node1, node2));
                    }
                }
            }
        }

        if(timer) {
            logger.info("Duration of graph trimming: " + (System.currentTimeMillis() - startTime));
        }

    }

    public boolean isPathBetween(int start, int end) {
//        Najnormalniejszy dfs który sprawdza czy istnieje ścieżka
        if (!nodes.contains(start) || !nodes.contains(end)) {
            return false;
        }

        Set<Integer> visited = new HashSet<>();

        return dfs(start, end, visited);
    }

    private boolean dfs(int current, int target, Set<Integer> visited) {
        if (current == target) {
            return true;
        }

        visited.add(current);

        for (Edge edge : edges) {
            if (edge.getSource() == current && !visited.contains(edge.getTarget())) {
                if (dfs(edge.getTarget(), target, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<Edge> generateEdgesFromDependency(HashSet<ArrayList<Indivisible>> dependency, ArrayList<Indivisible> word) {
        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 0; i < word.size() - 1; i++) {
            for (int j = i + 1; j < word.size(); j++) {
                ArrayList<Indivisible> temp = new ArrayList<>(List.of(word.get(i), word.get(j)));

                if (dependency.contains(temp)) {
                    edges.add(new Edge(i + 1, j + 1));
                }
            }
        }
        return edges;
    }

}
