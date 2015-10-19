package moal.util;

/**
 * Static utils for Array
 */
public class Array {

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

}
