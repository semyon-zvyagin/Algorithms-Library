package moal.array;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BinaryOperator;

/**
 * Contain static methods to find maximum subarray ( means two index head and tail,
 * that A[head] + ... + A[tail] >= A[i] + ... + A[j], where i, j any other indices
 */
public class FindMaximumSubarray {

    /**
     * Just Brute Force method,
     * complexity O(n^2)
     *
     * @param array       elements of array
     * @param subtraction for subtraction T elements
     * @param comparator  for comparing T elements
     * @param infinity    T element, that less then any other T element
     * @param <T>         type of array elements
     * @return cortege of head, tail and sum maximum subarray
     */
    static public <T> Optional<Answer<T>> bruteForce(T[] array, BinaryOperator<T> subtraction, Comparator<T> comparator, T infinity) {
        T max = infinity;
        int head = -1, tail = -1;

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                T current = subtraction.apply(array[i], array[j]);
                if (comparator.compare(current, max) == 1) {
                    max = current;
                    head = i;
                    tail = j;
                }
            }
        }

        return (max.equals(infinity) ? Optional.empty() : Optional.of(Answer.of(max, head, tail)));
    }

    /**
     * One more Brute Force Method, but used on difference array
     * look {@link moal.util.ArrayUtils#generateIntervalsArray(Object[], Class, BinaryOperator)}
     * complexity O(n^2)
     *
     * @param differenceArray elements of difference array
     * @param addition        for addition T elements
     * @param comparator      for comparing T elements
     * @param infinity        T element, that less any other T element
     * @param zero            T element, that neutral with respect to addition
     * @param <T>             type of array elements
     * @return cortege of head, tail and sum maximum subarray
     */
    static public <T> Optional<Answer<T>> differenceMethod(T[] differenceArray, BinaryOperator<T> addition, Comparator<T> comparator, T infinity, T zero) {
        T max = infinity;
        int head = -1, tail = -1;

        for (int i = 0; i < differenceArray.length; i++) {
            T sum = zero;
            for (int j = i; j < differenceArray.length; j++) {
                sum = addition.apply(sum, differenceArray[j]);
                if (comparator.compare(sum, max) == 1) {
                    max = sum;
                    head = i;
                    tail = j;
                }
            }
        }

        return (max.equals(infinity) ? Optional.empty() : Optional.of(Answer.of(max, head, tail)));
    }

    /**
     * Divide and Rule method for solve maximal subarray problem, needs difference array
     * look {@link moal.util.ArrayUtils#generateIntervalsArray(Object[], Class, BinaryOperator)}
     * complexity O(n * log(n))
     *
     * @param differenceArray elements of difference array
     * @param addition        for addition T elements
     * @param comparator      for comparing T elements
     * @param infinity        T element, that less any other T element
     * @param zero            T element, that neutral with respect to addition
     * @param <T>             type of array elements
     * @return cortege of head, tail and sum maximum subarray
     */
    static public <T> Optional<Answer<T>> divideAndRule(T[] differenceArray, BinaryOperator<T> addition, Comparator<T> comparator, T infinity, T zero) {
        return differenceArray.length == 0 ? Optional.empty() :
                Optional.of(divideAndRule(differenceArray, 0, differenceArray.length - 1, addition, comparator, infinity, zero));
    }

    /**
     * Recursive method that is called on two part of array A[low .. high], until
     * low != high, worked on principle "Divide and Rule", find max parts in left,
     * right and cross arrays, then comparing them and return maximal cortege
     * complexity O(n * log(n)), where
     * O(n) - {@link #findMaxCrossingSubArray(Object[], int, int, int, BinaryOperator, Comparator, Object, Object)}
     * O(log(n)) - recursive calls itself
     *
     * @param differenceArray elements of difference array
     * @param low             left index in array
     * @param high            right index of array
     * @param addition        for addition T elements
     * @param comparator      for comparing T elements
     * @param infinity        T element, that less any other T element
     * @param zero            T element, that neutral with respect to addition
     * @param <T>             type of array elements
     * @return cortege (sum, head, tail) of maximum subarray in A[low .. high]
     */
    static private <T> Answer<T> divideAndRule(T[] differenceArray, int low, int high, BinaryOperator<T> addition, Comparator<T> comparator, T infinity, T zero) {
        if (low == high) {
            return Answer.of(differenceArray[low], low, high);
        } else {
            int mid = low + (high - low) / 2;
            Answer<T> leftCortege = divideAndRule(differenceArray, low, mid, addition, comparator, infinity, zero);
            Answer<T> rightCortege = divideAndRule(differenceArray, mid + 1, high, addition, comparator, infinity, zero);
            Answer<T> crossCortege = findMaxCrossingSubArray(differenceArray, low, mid, high, addition, comparator, infinity, zero);
            if ((comparator.compare(leftCortege.getMax(), rightCortege.getMax()) >= 0) &&
                    (comparator.compare(leftCortege.getMax(), crossCortege.getMax()) >= 0)) {
                return leftCortege;
            } else if ((comparator.compare(rightCortege.getMax(), leftCortege.getMax()) >= 0) &&
                    (comparator.compare(rightCortege.getMax(), crossCortege.getMax()) >= 0)) {
                return rightCortege;
            } else {
                return crossCortege;
            }
        }
    }

    /**
     * Find maximum subarray in A[low .. mid .. high], that one part to the
     * left of the middle element, and the other to the right
     * complexity O(high - low)
     *
     * @param differenceArray elements of difference array
     * @param low             left index in array
     * @param mid             middle index of array
     * @param high            right index of array
     * @param addition        for addition T elements
     * @param comparator      for comparing T elements
     * @param infinity        T element, that less any other T element
     * @param zero            T element, that neutral with respect to addition
     * @param <T>             type of array elements
     * @return cortege (sum, head, tail) of maximum subarray in A[low .. mid .. high]
     */
    static private <T> Answer<T> findMaxCrossingSubArray(T[] differenceArray, int low, int mid, int high, BinaryOperator<T> addition, Comparator<T> comparator, T infinity, T zero) {
        T leftMaxSum = infinity;
        int iLeftMaxSum = -1;
        T sum = zero;

        for (int i = mid; i >= low; i--) {
            sum = addition.apply(sum, differenceArray[i]);
            if (comparator.compare(sum, leftMaxSum) == 1) {
                leftMaxSum = sum;
                iLeftMaxSum = i;
            }
        }

        T rightMaxSum = infinity;
        int iRightMaxSum = -1;
        sum = zero;

        for (int i = mid + 1; i <= high; i++) {
            sum = addition.apply(sum, differenceArray[i]);
            if (comparator.compare(sum, rightMaxSum) == 1) {
                rightMaxSum = sum;
                iRightMaxSum = i;
            }
        }

        return Answer.of(addition.apply(leftMaxSum, rightMaxSum), iLeftMaxSum, iRightMaxSum);
    }

    /**
     * Cortege of head index, tail index and sum = A[head] + ... + A[tail]
     * @param <T> type of array elements and respectively sum of elements one
     */
    static public class Answer<T> {

        private T max;
        private int head, tail;

        private Answer(T max, int head, int tail) {
            this.max = max;
            this.head = head;
            this.tail = tail;
        }

        static private <T> Answer<T> of(T max, int head, int tail) {
            return new Answer<>(max, head, tail);
        }

        public T getMax() {
            return max;
        }

        public int getHead() {
            return head;
        }

        public int getTail() {
            return tail;
        }
    }
}