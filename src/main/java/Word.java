import java.util.ArrayList;
import java.util.logging.Logger;

public class Word {

    private static final Logger logger = Logger.getLogger(Word.class.getName());

    Word() {

    }

    public static ArrayList<Indivisible> generateWord(int n, Boolean timer) {

        long startTime = System.currentTimeMillis();

        ArrayList<Indivisible> word = new ArrayList<Indivisible>();
        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                word.add(new A(j, i));
                for(int k = i; k <= n; k++) {
                    word.add(new B(j, i, k));
                }
                for(int k = i; k <= n; k++) {
                    word.add(new C(j, i, k));
                }
            }
        }

        if(timer) {
            logger.info("Duration of generating word list: " + (System.currentTimeMillis() - startTime));
        }

        return word;
    }

    public static ArrayList<Integer> generateWordInt(ArrayList<Indivisible> word, Boolean timer) {

        long startTime = System.currentTimeMillis();

        ArrayList<Integer> word_int = new ArrayList<>();
        for(int i = 1; i <= word.size(); i++) {
            word_int.add(i);
        }

        if(timer) {
            logger.info("Duration of generating word_int list: " + (System.currentTimeMillis() - startTime));
        }

        return word_int;
    }

}
