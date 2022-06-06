package ubc.cosc322;
import java.util.ArrayList;

public class Coordinates {
    private final ArrayList<Integer> array = new ArrayList<>(2);

    public Coordinates(int[] Coordinates) {
        array.add(Coordinates[0]);
        array.add(Coordinates[1]);
    }

    public int get(int i) {
        return array.get(i);
    }

    public int set(int i, Integer val) {
        return array.set(i, val);
    }

    public boolean equals(Object other) {
        if (!(other instanceof Coordinates))
            return false;

        return (array.get(0).equals(((Coordinates) other).get(0))) && (array.get(1).equals(((Coordinates) other).get(1)));
    }

    public String toString() {
        return "[" + array.get(0) + ", " + array.get(1) + "]";
    }

    public int hashCode() {
        return array.hashCode();
    }
}
