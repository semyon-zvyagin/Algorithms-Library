package moal.array;

import moal.generator.Generator;

import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {

    Integer[] array;
    int size = 10;
    Comparator<Integer> comparator = (x, y) -> (Integer.compare(x, y));
    Integer infinity = Integer.MAX_VALUE;

    @org.junit.Before
    public void setUp() throws Exception {
        array = Generator.getRandomIntegerArray(size, size);
        System.out.println(Arrays.toString(array));
    }

    @org.junit.After
    public void tearDown() throws Exception {
        System.out.println(Arrays.toString(array));
        for (int i = 1; i < array.length; i++) {
            assert comparator.compare(array[i - 1], array[i]) != 1;
        }
    }

    @org.junit.Test
    public void testMerge() throws Exception {
        Sorting.merge(array, comparator, infinity);
    }
}