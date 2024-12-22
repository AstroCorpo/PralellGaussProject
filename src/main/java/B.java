import java.util.Objects;

public class B extends Indivisible {

    private int k;

    B(int j, int i, int k) {
        super(j, i);
        this.k = k;
    }

    public int getK() {
        return this.k;
    }

    @Override
    public String toString() {
        return "B" + super.j + super.i + this.k;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Sprawdzenie, czy to ten sam obiekt
        if (o == null || getClass() != o.getClass()) return false; // Sprawdzenie klasy obiektu
        B b = (B) o; // Rzutowanie na klasę B
        return j == b.j && i == b.i && k == b.k; // Porównanie pól j, i oraz k
    }

    @Override
    public int hashCode() {
        return Objects.hash(j, i, k); // Generowanie hasha na podstawie j, i i k
    }
}
