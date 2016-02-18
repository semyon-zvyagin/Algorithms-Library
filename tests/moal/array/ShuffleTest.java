package moal.array;

import moal.generator.Generator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class ShuffleTest {

    Integer[] array;
    int size = 10;

    @Before
    public void setUp() throws Exception {
        array = Generator.getRandomIntegerArray(size, size);
        System.out.println(Arrays.toString(array));
    }

    @After
    public void tearDown() throws Exception {
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void testPermuteBySorting() throws Exception {
        Shuffle.permuteBySorting(array);
    }

    @Test
    public void testInPlace() throws Exception {
        Shuffle.inPlace(array);
    }
}