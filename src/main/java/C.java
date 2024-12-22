import java.util.Objects;

public class C extends Indivisible {

    private int k;

    C(int j, int i, int k) {
        super(j, i);
        this.k = k;
    }

    public int getK() {
        return this.k;
    }

    @Override
    public String toString() {
        return "C" + super.j + super.i + this.k;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Sprawdzenie, czy to ten sam obiekt
        if (o == null || getClass() != o.getClass()) return false; // Sprawdzenie typu klasy
        C c = (C) o; // Rzutowanie na klasę C
        return j == c.j && i == c.i && k == c.k; // Porównanie wartości pól j, i oraz k
    }

    @Override
    public int hashCode() {
        return Objects.hash(j, i, k); // Generowanie hasha na podstawie pól j, i oraz k
    }
}
