import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

    public class CustomReader {

        public CustomReader() {

        }

        public static int readMatrix(ArrayList<ArrayList<Double>> matrix, String filePath) {
            int size = 0;
            ArrayList<Double> vector = new ArrayList<>();

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
                // Odczytaj pierwszy wiersz - wielkość macierzy
                String line = bufferedReader.readLine();
                size = Integer.parseInt(line.trim());

                // Odczytaj wiersze macierzy
                for (int i = 0; i < size; i++) {
                    line = bufferedReader.readLine();
                    String[] values = line.split("\\s+");
                    ArrayList<Double> row = new ArrayList<>();
                    for (String value : values) {
                        row.add(Double.parseDouble(value));
                    }
                    matrix.add(row);
                }

                // Odczytaj ostatni wiersz - wektor
                line = bufferedReader.readLine();
                if (line != null) {
                    String[] values = line.split("\\s+");
                    for (String value : values) {
                        vector.add(Double.parseDouble(value));
                    }
                }

                // Dodanie z prawej wektoru do macierzy
                for(int i = 0; i < size; i++) {
                    matrix.get(i).add(vector.get(i));
                }

            } catch (IOException e) {
                System.err.println("Błąd podczas odczytu pliku: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Błąd formatu danych w pliku: " + e.getMessage());
            }

            return size;
        }

    }
