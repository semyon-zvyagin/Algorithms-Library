package moal.array;

import moal.util.Array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;

public class Sorting {

    public static <T> void insertion(T[] array, Comparator<T> comparator) {
        for (int j = 0; j < array.length; j++) {
            T key = array[j];
            int i = j - 1;

            while ((i >= 0) && (comparator.compare(array[i], key) == 1)) {
                array[i + 1] = array[i];
                i = i - 1;
            }
            array[i + 1] = key;
        }
    }

    public static <T> void bubble(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (comparator.compare(array[j], array[j - 1]) == -1)
                    Array.swap(array, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());
        IntStream source = random.ints(0, 100);

        Integer[] array = source.limit(100).boxed().toArray(Integer[]::new);

        System.out.println(Arrays.toString(array));
        //insertion(array, (x, y) -> (Integer.compare(x, y)));
        bubble(array, (x, y) -> (Integer.compare(x, y)));
        System.out.println(Arrays.toString(array));
    }
}
