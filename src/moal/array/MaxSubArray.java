package moal.array;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BinaryOperator;

/**
 * Contain static methods to find maximum sub array
 */
public class MaxSubArray {

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