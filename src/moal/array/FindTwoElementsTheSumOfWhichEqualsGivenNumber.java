package moal.array;

import java.util.*;
import java.util.function.BinaryOperator;

/**
 * Yeah, bad named, but I did not come up with anything better
 */
public class FindTwoElementsTheSumOfWhichEqualsGivenNumber {

    /**
     * Review all the pairs in the array. When find first one, return it.
     * Complexity = O(n^2)
     *
     * @param array       of elements
     * @param givenNumber sum of two elements
     * @param addition    for addition
     * @param <T>         type of array elements
     * @return Cortege of two index of array
     */
    public static <T> Optional<IndexAnswer> bruteForce(T[] array, T givenNumber, BinaryOperator<T> addition) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (addition.apply(array[i], array[j]).equals(givenNumber)) {
                    return Optional.of(IndexAnswer.of(i, j));
                }
            }
        }
        return Optional.empty();
    }

    /**
     * First, we sort the array and then looping through each element and
     * trying to find a pair for the remainder of the array using binary
     * search. When find first one, return it.
     * Complexity = O(n * log(n))
     *
     * @param array       of elements
     * @param givenNumber sum of two elements
     * @param subtraction to define search number of pair
     * @param comparator  for sort method
     * @param <T>         type of array elements
     * @return Cortege of two index of array
     */
    public static <T> Optional<IndexAnswer> sortAndBinarySearch(T[] array, T givenNumber, BinaryOperator<T> subtraction, Comparator<T> comparator) {
        Arrays.sort(array, comparator);
        int length = array.length;
        int last = length - 1;
        for (int i = 0; i < last; i++) {
            int pair = Arrays.binarySearch(array, i + 1, length, subtraction.apply(givenNumber, array[i]));
            if (pair > 0) {
                return Optional.of(IndexAnswer.of(i, pair));
            }
        }
        return Optional.empty();
    }

    /**
     * First, we sort the array and then looping through each element
     * skipping duplicates and trying to find a pair for the remainder
     * of the array using binary search. When find first one, return it.
     * Complexity = O(n * log(n))
     *
     * @param array       of elements
     * @param givenNumber sum of two elements
     * @param subtraction to define search number of pair
     * @param comparator  for sort method
     * @param <T>         type of array elements
     * @return Cortege of two index of array
     */
    public static <T> Optional<IndexAnswer> sortAndBinarySearchIgnoreDuplicates(T[] array, T givenNumber, BinaryOperator<T> subtraction, Comparator<T> comparator) {
        Arrays.sort(array, comparator);
        int length = array.length;
        int last = length - 1;
        int penultimate = last - 2;
        for (int i = 0; i < last; i++) {

            int pair = Arrays.binarySearch(array, i + 1, length, subtraction.apply(givenNumber, array[i]));
            if (pair > 0) {
                return Optional.of(IndexAnswer.of(i, pair));
            }

            while (i != penultimate && array[i].equals(array[i + 1])) { //skip duplicates
                i++;
            }
        }
        return Optional.empty();
    }

    /**
     * Looping through each element X of array, looking in HashMap
     * for key X, if find return pair (key, value), if not add
     * new (key, value) = (givenNumber - X, X).
     * Complexity = O(n)
     *
     * @param array        of elements
     * @param givenNumber  sum of two elements
     * @param subtraction  to define key of HashMap element
     * @param <T>          type of array elements
     * @return Cortege of two elements of array
     */
    public static <T> Optional<TypeAnswer> hashSearch(T[] array, T givenNumber, BinaryOperator<T> subtraction) {
        Map<T, T> pairs = new HashMap<>();
        for (T element : array) {
            if (pairs.containsKey(element)) {
                return Optional.of(TypeAnswer.of(element, pairs.get(element)));
            } else {
                pairs.put(subtraction.apply(givenNumber, element), element);
            }
        }
        return Optional.empty();
    }


    /**
     * Cortege of two indexes
     */
    static public class IndexAnswer {
        private int first, second;

        private IndexAnswer(int first, int second) {
            this.first = first;
            this.second = second;
        }

        static private IndexAnswer of(int first, int second) {
            return new IndexAnswer(first, second);
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }
    }

    /**
     * Cortege of two elements
     */
    static public class TypeAnswer<T> {
        private T first, second;

        private TypeAnswer(T first, T second) {
            this.first = first;
            this.second = second;
        }

        static private <T> TypeAnswer of(T first, T second) {
            return new TypeAnswer(first, second);
        }

        public T getFirst() {
            return first;
        }

        public T getSecond() {
            return second;
        }
    }
}
