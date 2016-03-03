package moal.structure.mutable;

import moal.util.ArrayUtils;

import java.util.Comparator;

public class MaxHeap<T> {
    private final T[] array;
    private final Comparator<T> comparator;
    private int size = 0;

    public MaxHeap(T[] array, Comparator<T> comparator) {
        this.array = array;
        this.comparator = comparator;
        buildMaxHeap();
    }

    private static int parent(int index) {
        return (index - 1) >> 1;
    }

    private static int left(int index) {
        return (index << 1) | 0b1;
    }

    private static int right(int index) {
        return left(index) + 1;
    }

    /**
     * Move element on the right place
     *
     * @param index of element
     */
    private void maxHeapify(int index) {
        int left = left(index);
        int right = right(index);

        int largest = index;

        if ((left < size) && (comparator.compare(array[left], array[largest]) == 1)) {
            largest = left;
        }

        if ((right < size) && (comparator.compare(array[right], array[largest]) == 1)) {
            largest = right;
        }

        if (largest != index) {
            ArrayUtils.swap(array, largest, index);
            maxHeapify(largest);
        }
    }

    /**
     * Build MaxHeap structure, starting from parent last element
     * witch move to right place
     */
    private void buildMaxHeap() {
        size = array.length;
        for (int i = parent(size - 1); i >= 0; i--) {
            maxHeapify(i);
        }
    }

    /**
     * Heap sorting array
     * Complexity O(n * log(n))
     *
     * @return sorted array
     */
    public T[] heapSort() {
        for (int i = array.length - 1; i > 0; i--) {
            ArrayUtils.swap(array, 0, i);
            size--;
            maxHeapify(0);
        }
        return array;
    }

    /**
     * @return maximal element
     */
    public T maximum() {
        return array[0];
    }

    /**
     * Extract maximal element from array
     *
     * @return maximal element
     */
    public T extractMax() {
        if (size == 0) {
            return null;
        }
        T max = array[0];
        array[0] = array[--size];
        maxHeapify(0);
        return max;
    }
}