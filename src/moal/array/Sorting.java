package moal.array;

import moal.util.Array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Class with static methods for sorting array
 */
public class Sorting {

    /**
     * Sorting array with Insertion
     * Complexity O(n^2), compare count O(n^2)
     *
     * @param array      for sorting
     * @param comparator for comparing types
     * @param <T>        type for array and comparator
     */
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

    /**
     * Sorting array with Insertion method, but for search in sorted array
     * use Binary Search Algorithm
     * Complexity O(n^2), but compare count O(n * log(n))
     * @param array for sorting
     * @param comparator for comparing types
     * @param <T> type for array and comparator
     */
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

    /**
     * Sorting array with Insertion method, but for search in sorted array
     * use Binary Search Algorithm
     * Complexity O(n^2), but compare count O(n * log(n))
     * @param array for sorting
     * @param comparator for comparing types
     * @param from index (include) will sort
     * @param to index (include) will sort
     * @param <T> type for array and comparator
     */
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

    /**
     * Sorting array with Bubble method
     * Complexity O(n^2)
     * @param array for sorting
     * @param comparator for comparing types
     * @param <T> type for array and comparator
     */
    public static <T> void bubble(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (comparator.compare(array[j], array[j - 1]) == -1)
                    Array.swap(array, j, j - 1);
            }
        }
    }

    /**
     * Sorting array with Merge method
     * Complexity O(n * log(n))
     * @param array for sorting
     * @param comparator for comparing types
     * @param infinity elements, that comparator(infinity,T) always equals 1
     * @param <T> type for array and comparator
     */
    public static <T> void merge(T[] array, Comparator<T> comparator, T infinity) {
        merge(array, 0, array.length - 1, comparator, infinity);
    }

    /**
     * Recursive method that first call himself for two parts for array
     * and after that merge them saved order
     * Complexity O(n * log(n))
     * @param array for sorting
     * @param p index from (inclusive) will sort
     * @param r index to (inclusive) will sort
     * @param comparator for comparing types
     * @param infinity elements, that comparator(infinity,T) always equals 1
     * @param <T> type for array and comparator
     */
    private static <T> void merge(T[] array, int p, int r, Comparator<T> comparator, T infinity) {
        if (p < r) {
            int q = (r + p) >> 1;
            merge(array, p, q, comparator, infinity);
            merge(array, q + 1, r, comparator, infinity);
            merge(array, p, q, r, comparator, infinity);
        }
    }

    /**
     * Method for merging two ordered array
     * @param array source for elements
     * @param p index from (inclusive) start first order array
     * @param q index to (inclusive) ends first ordered array, and
     *          also q+1 will start for second ordered array
     * @param r index to (inclusive) ends second ordered array
     * @param comparator for comparing types
     * @param infinity elements, that comparator(infinity,T) always equals 1
     * @param <T> type for array and comparator
     */
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

    /**
     * Sorting array with Merge method without using Infinity flags
     * Complexity O(n * log(n))
     * @param array for sorting
     * @param comparator for comparing types
     * @param <T> type for array and comparator
     */
    public static <T> void merge(T[] array, Comparator<T> comparator) {
        merge(array, 0, array.length - 1, comparator);
    }

    /**
     * Recursive method that first call himself for two parts for array
     * and after that merge them saved order
     * Complexity O(n * log(n))
     * @param array for sorting
     * @param p index from (inclusive) will sort
     * @param r index to (inclusive) will sort
     * @param comparator for comparing types
     * @param <T> type for array and comparator
     */
    private static <T> void merge(T[] array, int p, int r, Comparator<T> comparator) {
        if (p < r) {
            int q = (r + p) >> 1;
            merge(array, p, q, comparator);
            merge(array, q + 1, r, comparator);
            merge(array, p, q, r, comparator);
        }
    }

    /**
     * Sorting array with Merge method without using Infinity flag
     * Complexity O(n * log(n))
     * @param array for sorting
     * @param comparator for comparing types
     * @param <T> type for array and comparator
     * @param k count for elements when need use Insertion method
     */
    public static <T> void mergeWithInsertion(T[] array, Comparator<T> comparator, int k) {
        mergeWithInsertion(array, 0, array.length - 1, comparator, k);
    }

    /**
     * Recursive method that if array.length will less then k, will used Insertion
     * method sort or if more then call himself for two parts for array
     * and after that merge them saved order
     * Complexity O(n * log(n))
     * @param array for sorting
     * @param p index from (inclusive) will sort
     * @param r index to (inclusive) will sort
     * @param comparator for comparing types
     * @param <T> type for array and comparator
     * @param k count for elements when need use Insertion method
     */
    private static <T> void mergeWithInsertion(T[] array, int p, int r, Comparator<T> comparator, int k) {
        if (r - p + 1 > k) {
            int q = (r + p) >> 1;
            merge(array, p, q, comparator);
            merge(array, q + 1, r, comparator);
            merge(array, p, q, r, comparator);
        } else {
            insertionBinary(array, comparator, p, r);
        }
    }

    /**
     * Method for merging two ordered array
     * @param array source for elements
     * @param p index from (inclusive) start first order array
     * @param q index to (inclusive) ends first ordered array, and
     *          also q+1 will start for second ordered array
     * @param r index to (inclusive) ends second ordered array
     * @param comparator for comparing types
     * @param <T> type for array and comparator
     */
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
