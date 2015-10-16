package moal.array;

import moal.util.Array;

import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T> void insertion(T[] array, Comparator<T> comparator) {
        for (int j = 1; j < array.length; j++) {
            T key = array[j];
            int i = j - 1;

            while ((i >= 0) && (comparator.compare(array[i], key) == 1)) {
                array[i + 1] = array[i];
                i = i - 1;
            }
            array[i + 1] = key;
        }
    }

    public static <T> void insertionBinary(T[] array, Comparator<T> comparator) {
        for (int j = 1; j < array.length; j++) {
            T key = array[j];

            int place = Arrays.binarySearch(array, 0, j, key, comparator);
            if (place < 0) {
                place = -(place + 1);
            } else {
                place++;
            }

            System.arraycopy(array, place, array, place + 1, j - place);
            array[place] = key;
        }
    }

    private static <T> void insertionBinary(T[] array, Comparator<T> comparator, int from, int to) {
        for (int j = from + 1; j <= to; j++) {
            T key = array[j];

            int place = Arrays.binarySearch(array, from, j, key, comparator);
            if (place < 0) {
                place = -(place + 1);
            } else {
                place++;
            }

            System.arraycopy(array, place, array, place + 1, j - place);
            array[place] = key;
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

    private static <T> void merge(T[] array, int p, int r, Comparator<T> comparator, T infinity) {
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

        int i = 0, j = 0;

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

    public static <T> void merge(T[] array, Comparator<T> comparator) {
        merge(array, 0, array.length - 1, comparator);
    }

    private static <T> void merge(T[] array, int p, int r, Comparator<T> comparator) {
        if (p < r) {
            int q = (r + p) >> 1;
            merge(array, p, q, comparator);
            merge(array, q + 1, r, comparator);
            merge(array, p, q, r, comparator);
        }
    }

    public static <T> void mergeWithInsertion(T[] array, Comparator<T> comparator, int k) {
        mergeWithInsertion(array, 0, array.length - 1, comparator, k);
    }

    private static <T> void mergeWithInsertion(T[] array, int p, int r, Comparator<T> comparator, int k) {
        if (p < r) {
            int q = (r + p) >> 1;

            if (q - p + 1 > k)
                merge(array, p, q, comparator);
            else
                insertionBinary(array, comparator, p, q);

            if (r - q > k)
                merge(array, q + 1, r, comparator);
            else
                insertionBinary(array, comparator, q + 1, r);

            merge(array, p, q, r, comparator);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> void merge(T[] array, int p, int q, int r, Comparator<T> comparator) {
        int sizeLeft = q - p + 1;
        int sizeRight = r - q;

        Object[] left = new Object[sizeLeft];
        Object[] right = new Object[sizeRight];

        System.arraycopy(array, p, left, 0, sizeLeft);
        System.arraycopy(array, q + 1, right, 0, sizeRight);

        Object[] atFirst, andThen;
        if (comparator.compare((T) left[sizeLeft - 1], (T) right[sizeRight - 1]) == -1) {
            atFirst = left;
            andThen = right;
        } else {
            atFirst = right;
            andThen = left;
        }

        int i = 0, j = 0, k = p;

        while (i < atFirst.length) {
            if (comparator.compare((T) atFirst[i], (T) andThen[j]) < 1) {
                array[k] = (T) atFirst[i];
                i++;
            } else {
                array[k] = (T) andThen[j];
                j++;
            }
            k++;
        }

        while (j < andThen.length) {
            array[k] = (T) andThen[j];
            j++;
            k++;
        }
    }
}
