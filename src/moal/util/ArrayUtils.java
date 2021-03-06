package moal.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.function.BinaryOperator;

/**
 * Static utils for Array
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

    /**
     * Create String array with same length as input array, where elements
     * represented as a string representation of input array
     *
     * @param array input elements
     * @return String array
     */
    public static String[] convertToStringArray(Object[] array) {
        return Arrays.stream(array).map(Object::toString).toArray(String[]::new);
    }

    /**
     * Create LinkedList with elements of input array
     *
     * @param <T>   type of input array and output LinkedList
     * @param array input elements
     * @return LinkedList
     */
    public static <T> LinkedList<T> convertToLinkedList(T[] array) {
        LinkedList<T> result = new LinkedList<>();
        Collections.addAll(result, array);
        return result;
    }
}
