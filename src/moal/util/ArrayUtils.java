package moal.util;

import java.lang.reflect.Array;
import java.util.function.BinaryOperator;

/**
 * Static utils for ArrayUtils
 */
public class ArrayUtils {

    /**
     * Swap two elements of array. I wait a,b = b,a in Java :(
     *
     * @param array  of elements
     * @param first  element
     * @param second element
     * @param <T>    of array
     */
    public static <T> void swap(T[] array, int first, int second) {
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    /**
     * Creates an array of differences between adjacent elements of the input array
     *
     * @param array       of input elements
     * @param subtraction BiFunction for taking difference between two elements of array
     * @param <T>         the type of the input arguments of the comparator
     * @return an array representing the difference between the neighbor elements of the input array
     */
    public static <T> T[] generateIntervalsArray(T[] array, Class<T> clazz, BinaryOperator<T> subtraction) {

        int resultArrayLength = array.length - 1;
        @SuppressWarnings("unchecked")
        T[] result = (T[]) Array.newInstance(clazz, resultArrayLength);

        for (int i = 0; i < resultArrayLength; i++) {
            result[i] = subtraction.apply(array[i], array[i + 1]);
        }

        return result;
    }
}
