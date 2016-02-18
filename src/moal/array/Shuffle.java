package moal.array;

import moal.generator.Generator;
import moal.structure.immutable.Pair;
import moal.util.ArrayUtils;

import java.util.PriorityQueue;

public class Shuffle {

    static Distribution uniform = Generator::getRandomInteger;

    public static <T> T[] permuteBySorting(T[] array) {
        return permuteBySorting(array, uniform);
    }

    public static <T> T[] permuteBySorting(T[] array, Distribution distribution) {
        int length = array.length;
        PriorityQueue<Pair<Integer, T>> queue = new PriorityQueue<>(length, (x, y) -> x.A.compareTo(y.A));

        int borderOfRandom = length * length * length;
        for (T element : array) {
            queue.add(Pair.of(distribution.next(0, borderOfRandom), element));
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = queue.remove().B;
        }

        return array;
    }

    public static <T> T[] inPlace(T[] array) {
        return inPlace(array, uniform);
    }

    public static <T> T[] inPlace(T[] array, Distribution distribution) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            ArrayUtils.swap(array, i, distribution.next(i, length));
        }
        return array;
    }

    interface Distribution {
        int next(int leftInclusive, int rightExclusive);
    }
}
