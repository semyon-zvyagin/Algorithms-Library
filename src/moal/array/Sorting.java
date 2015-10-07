package moal.array;

import moal.util.Array;

import java.util.Comparator;

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

    public static <T> void merge(T[] array, Comparator<T> comparator, T infinity) {
        merge(array, 0, array.length - 1, comparator, infinity);
    }

    public static <T> void merge(T[] array, int p, int r, Comparator<T> comparator, T infinity) {
        if (p < r) {
            int q = (r + p) >> 1;
            merge(array, p, q, comparator, infinity);
            merge(array, q + 1, r, comparator, infinity);
            merge(array, p, q, r, comparator, infinity);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> void merge(T[] array, int p, int q, int r, Comparator<T> comparator, T infinity) {
        int sizeLeft = q - p + 1;
        int sizeRight = r - q;

        Object[] left = new Object[sizeLeft + 1];
        Object[] right = new Object[sizeRight + 1];

        System.arraycopy(array, p, left, 0, sizeLeft);
        System.arraycopy(array, q + 1, right, 0, sizeRight);

        left[sizeLeft] = infinity;
        right[sizeRight] = infinity;

        int i = 0;
        int j = 0;

        for (int k = p; k <= r; k++) {
            if (comparator.compare((T) left[i], (T) right[j]) < 1) {
                array[k] = (T) left[i];
                i++;
            } else {
                array[k] = (T) right[j];
                j++;
            }
        }
    }
}
