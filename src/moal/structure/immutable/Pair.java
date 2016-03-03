package moal.structure.immutable;

public class Pair<T, V> {
    public final T A;
    public final V B;

    private Pair(T A, V B) {
        this.A = A;
        this.B = B;
    }

    @SuppressWarnings("unchecked")
    public static <T, V> Pair<T, V> of(T A, V B) {
        return new Pair(A, B);
    }

    @Override
    public String toString() {
        return String.format("<%s,%s>", A, B);
    }
}
