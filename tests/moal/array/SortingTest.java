package moal.array;

import moal.generator.Generator;
import moal.generator.entity.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

public class SortingTest {

    Person[] array;
    int size = 0xfff;
    Comparator<Person> comparator = (x, y) -> (Integer.compare(x.getId(), y.getId()));
    Person infinity = new Person(Integer.MAX_VALUE, "Infinity");
    IntFunction<Person[]> generator = Person[]::new;
    ToIntFunction<Person> key = Person::getId;

    @org.junit.Before
    public void setUp() throws Exception {
        array = Generator.getRandomPersonArray(size);
        Shuffle.inPlace(array);
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

    @org.junit.Test
    public void testMergeWithoutInfinity() throws Exception {
        Sorting.merge(array, comparator);
    }

    @org.junit.Test
    public void testInsertionBinary() throws Exception {
        Sorting.insertionBinary(array, comparator);
    }

    @Test
    public void testMergeWithInsertion() throws Exception {
        Sorting.mergeWithInsertion(array, comparator, 2);
    }

    @Test
    public void testHeap() throws Exception {
        Sorting.heap(array, comparator);
    }

    @Test
    public void testQuick() throws Exception {
        Sorting.quick(array, comparator);
    }

    @Test
    public void testQuickRandomized() throws Exception {
        Sorting.quickRandomized(array, comparator);
    }

    @Test
    public void testCounting() throws Exception {
        array = Sorting.counting(array, generator, key, size);
    }

    @Test
    public void testRadix() throws Exception {
        array = Sorting.radix(array, generator, key, size);
    }
}