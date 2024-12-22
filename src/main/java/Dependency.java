import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Logger;

public class Dependency {

    private static final Logger logger = Logger.getLogger(Dependency.class.getName());

    Dependency() {

    }

    public static ArrayList<ArrayList<Indivisible>> AtoB(int n) {
        ArrayList<ArrayList<Indivisible>> dependency_1 = new ArrayList<ArrayList<Indivisible>>();
        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                for(int k = i; k <= n; k++) {
                    ArrayList<Indivisible> temp = new ArrayList<>();
                    temp.add(new A(j, i));
                    temp.add(new B(j, i, k));
                    dependency_1.add(temp);
                }
            }
        }
        return dependency_1;
    }

    public static ArrayList<ArrayList<Indivisible>> BtoC(int n) {
        ArrayList<ArrayList<Indivisible>> dependency_2 = new ArrayList<ArrayList<Indivisible>>();
        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                for(int k = i; k <= n; k++) {
                    ArrayList<Indivisible> temp = new ArrayList<>();
                    temp.add(new B(j, i, k));
                    temp.add(new C(j, i, k));
                    dependency_2.add(temp);
                }
            }
        }
        return dependency_2;
    }

    public static ArrayList<ArrayList<Indivisible>> CtoA(int n) {
        ArrayList<ArrayList<Indivisible>> dependency_3 = new ArrayList<ArrayList<Indivisible>>();
        for(int i = 1; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                int k = i;
                for(int a = 0; a < i; a++) {
                    ArrayList<Indivisible> temp = new ArrayList<>();
                    temp.add(new C(j, a, k));
                    temp.add(new A(j, i));
                    dependency_3.add(temp);
                    temp = new ArrayList<>();
                    temp.add(new C(i, a, k));
                    temp.add(new A(j, i));
                    dependency_3.add(temp);
                }
            }
        }
        return dependency_3;
    }

    public static ArrayList<ArrayList<Indivisible>> CtoB(int n) {
        ArrayList<ArrayList<Indivisible>> dependency_4 = new ArrayList<ArrayList<Indivisible>>();
        for(int i = 1; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                for(int k = i; k <= n; k++) {
                        ArrayList<Indivisible> temp = new ArrayList<>();
                        temp.add(new C(j, i-1, k));
                        temp.add(new B(j, i, k));
                        dependency_4.add(temp);
                }
            }
        }
        return dependency_4;
    }

    public static ArrayList<ArrayList<Indivisible>> CtoC(int n) {
        ArrayList<ArrayList<Indivisible>> dependency_5 = new ArrayList<ArrayList<Indivisible>>();
        for(int i = 1; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                for(int k = i; k <= n; k++) {
                    ArrayList<Indivisible> temp = new ArrayList<>();
                    temp.add(new C(j-1, i-1, k));
                    temp.add(new C(j, i, k));
                    dependency_5.add(temp);
                    temp = new ArrayList<>();
                    temp.add(new C(j, i-1, k));
                    temp.add(new C(j, i, k));
                    dependency_5.add(temp);
                }
            }
        }
        return dependency_5;
    }

    public static HashSet<ArrayList<Indivisible>> generateDependency(int n, Boolean timer) {

        long startTime = System.currentTimeMillis();

        ArrayList<ArrayList<Indivisible>> temp_dependency = new ArrayList<ArrayList<Indivisible>>();
        temp_dependency.addAll(AtoB(n));
        temp_dependency.addAll(BtoC(n));
        temp_dependency.addAll(CtoA(n));
        temp_dependency.addAll(CtoB(n));
        temp_dependency.addAll(CtoC(n));

        HashSet<ArrayList<Indivisible>> dependency = new HashSet(temp_dependency);

        if(timer) {
            logger.info("Duration of computing dependency set: " + (System.currentTimeMillis() - startTime));
        }

        return dependency;
    }

}
