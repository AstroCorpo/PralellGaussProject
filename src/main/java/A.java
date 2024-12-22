import java.util.Objects;

public class A extends Indivisible {

    A(int j, int i) {
        super(j, i);
    }

    @Override
    public String toString() {
        return "A" + super.j + super.i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Sprawdzenie, czy porównujemy ten sam obiekt
        if (o == null || getClass() != o.getClass()) return false; // Typ obiektu musi być taki sam
        A a = (A) o; // Rzutowanie na klasę A
        return j == a.j && i == a.i; // Porównanie wartości pól j i i
    }

    @Override
    public int hashCode() {
        return Objects.hash(j, i); // Generowanie unikalnego hasha na podstawie j i i
    }
}
