package moal.structure.mutable;

import moal.generator.Generator;
import moal.structure.immutable.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class MaxHeapTest {
    int size = 10;
    Pair<Integer, String>[] queue = new Pair[size];
    MaxHeap<Pair<Integer, String>> maxHeap;
    Comparator<Pair<Integer, String>> comparator = (x, y) -> (Integer.compare(x.A, y.A));

    @org.junit.Before
    public void setUp() throws Exception {
        for (int i = 0; i < queue.length; i++) {
            queue[i] = Pair.of(Generator.getRandomInteger(0, size), String.format("%d person", i));
        }
        System.out.println(Arrays.toString(queue));
        maxHeap = new MaxHeap<>(queue, comparator);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        System.out.println(Arrays.toString(queue));
    }

    @Test
    public void testMaximum() throws Exception {
        System.out.println(maxHeap.maximum());
    }

    @Test
    public void testExtractMax() throws Exception {
        Pair<Integer, String> top = maxHeap.extractMax();
        if (top != null) {
            do {
                System.out.println(top);
                top = maxHeap.extractMax();
            } while (top != null);
        }
    }
}