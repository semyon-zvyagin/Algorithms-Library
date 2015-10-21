package moal.array;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class MaxSubArrayTest {

    Integer[] array;
    Optional<MaxSubArray.Answer<Integer>> answer;
    Comparator<Integer> comparator = Integer::compare;
    BinaryOperator<Integer> subtraction = (a, b) -> (b - a);
    Integer infinity = Integer.MIN_VALUE;

    @Before
    public void setUp() throws Exception {
        array = new Integer[]{100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97};
    }

    @After
    public void tearDown() throws Exception {
        assert answer.isPresent();
        answer.ifPresent(a -> System.out.println(String.format("Max: %d, Head: %d, Tail: %d", a.getMax(), a.getHead(), a.getTail())));
    }

    @Test
    public void testBruteForce() throws Exception {
        answer = MaxSubArray.bruteForce(array, subtraction, comparator, infinity);
    }
}