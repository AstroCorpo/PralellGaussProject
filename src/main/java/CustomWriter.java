import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CustomWriter {

    CustomWriter() {

    }

    public static void writeMatrix(ArrayList<ArrayList<Double>> matrix, String filePath)
    {
        int n = matrix.size();
        try (FileWriter writer = new FileWriter(filePath))
        {
            writer.write(n + "\n");
            for(ArrayList<Double> row : matrix) {
                String row_string = "";
                for(int i = 0; i < row.size()-1; i++) {
                    row_string += row.get(i) + " ";
                }
                row_string += "\n";
                writer.write(row_string);
            }
            String vector_string = "";
            for(ArrayList<Double> row : matrix) {
                vector_string += row.get(row.size()-1) + " ";
            }
            writer.write(vector_string);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void writeGraphToDot(EdgeGraph graph, ArrayList<Indivisible> word, String savePath) {
        try (FileWriter writer = new FileWriter(savePath)) {
            writer.write("digraph g {\n");
            for (Edge edge : graph.getEdges()) {
                writer.write("\t" + edge.getSource() + " -> " + edge.getTarget() + ";" + "\n");
            }
            writer.write("\n");
            for (int i = 1; i < word.size() + 1; i++) {
                writer.write("\t" + i + " [label=\"" + word.get(i - 1) + "\"];\n");
            }
            writer.write("}");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
