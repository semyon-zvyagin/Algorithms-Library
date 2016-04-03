package moal.array;

import moal.generator.Generator;
import moal.structure.mutable.MaxHeap;
import moal.util.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

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
                    ArrayUtils.swap(array, j, j - 1);
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

    /**
     * Heap sorting method by using {@link moal.structure.mutable.MaxHeap} structure
     * Complexity O(n * log(n))
     *
     * @param array      of T elements
     * @param comparator for comparing T elements
     * @param <T>        type for array and comparator
     */
    public static <T> void heap(T[] array, Comparator<T> comparator) {
        MaxHeap<T> maxHeap = new MaxHeap<>(array, comparator);
        maxHeap.heapSort();
    }

    /**
     * @param array      of T elements
     * @param comparator for comparing T elements
     * @param <T>        type of array and comparator
     * @see <a href="https://en.wikipedia.org/wiki/Quicksort">Quick sorting</a> algorithm
     * Complexity O(n * log(n))
     */
    public static <T> void quick(T[] array, Comparator<T> comparator) {
        quick(array, 0, array.length - 1, comparator);
    }

    /**
     * Recursive method, that choose delimiter element, divides the array into two parts and
     * call itself on each part.
     * Complexity O(n * log(n))
     *
     * @param array      of T elements
     * @param p          index from (inclusive) will partition on two parts
     * @param r          index to (inclusive) will partition on two parts
     * @param comparator for comparing T elements
     * @param <T>        type of array and comparator
     */
    private static <T> void quick(T[] array, int p, int r, Comparator<T> comparator) {
        if (p < r) {
            int q = partition(array, p, r, comparator);
            quick(array, p, q - 1, comparator);
            quick(array, q + 1, r, comparator);
        }
    }

    /**
     * Selects the delimiting element with r index. Then puts less then delimiter elements
     * on the left of delimiter, and more then delimiter on the right.
     * Complexity O(n)
     *
     * @param array      of T elements
     * @param p          index from (inclusive) will partition on two parts
     * @param r          index to (inclusive) will partition on two parts
     * @param comparator for comparing T elements
     * @param <T>        type of elements
     * @return index of delimiter
     */
    private static <T> int partition(T[] array, int p, int r, Comparator<T> comparator) {
        T delimiter = array[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (comparator.compare(array[j], delimiter) != 1) {
                ArrayUtils.swap(array, ++i, j);
            }
        }
        ArrayUtils.swap(array, ++i, r);
        return i;
    }

    /**
     * Selects the delimiting element with random index. Then swap with element with r index.
     * Then call {@link #partition(Object[], int, int, Comparator)} method
     * Complexity O(n)
     *
     * @param array      of T elements
     * @param p          index from (inclusive) will partition on two parts
     * @param r          index to (inclusive) will partition on two parts
     * @param comparator for comparing T elements
     * @param <T>        type of elements
     * @return index of delimiter
     */
    private static <T> int partitionRandomized(T[] array, int p, int r, Comparator<T> comparator) {
        int randomDelimiterIndex = Generator.getRandomInteger(p, r + 1);
        ArrayUtils.swap(array, r, randomDelimiterIndex);
        return partition(array, p, r, comparator);
    }

    /**
     * Recursive method, that choose delimiter element, divides the array into two parts and
     * call itself on each part.
     * Complexity O(n * log(n))
     *
     * @param array      of T elements
     * @param p          index from (inclusive) will partition on two parts
     * @param r          index to (inclusive) will partition on two parts
     * @param comparator for comparing T elements
     * @param <T>        type of array and comparator
     */
    private static <T> void quickRandomized(T[] array, int p, int r, Comparator<T> comparator) {
        if (p < r) {
            int q = partitionRandomized(array, p, r, comparator);
            quickRandomized(array, p, q - 1, comparator);
            quickRandomized(array, q + 1, r, comparator);
        }
    }

    /**
     * @param array      of T elements
     * @param comparator for comparing T elements
     * @param <T>        type of array and comparator
     * @see <a href="https://en.wikipedia.org/wiki/Quicksort">Quick sorting</a> algorithm with
     * randomized delimiter element to become independent of the input data
     * Complexity O(n * log(n))
     */
    public static <T> void quickRandomized(T[] array, Comparator<T> comparator) {
        quickRandomized(array, 0, array.length - 1, comparator);
    }

    public static <T> T[] counting(T[] array, IntFunction<T[]> generator, ToIntFunction<T> key, int border) {
        int[] counter = new int[++border];

        for (T element : array) {
            counter[key.applyAsInt(element)]++;
        }

        for (int k = 1; k < border; k++) {
            counter[k] += counter[k - 1];
        }

        T[] sorted = generator.apply(array.length);
        for (int i = array.length - 1; i >= 0; i--) {
            int k = key.applyAsInt(array[i]);
            sorted[counter[k] - 1] = array[i];
            counter[k]--;
        }

        return sorted;
    }
}
