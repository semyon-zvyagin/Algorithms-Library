package moal.array;

import moal.util.ArrayUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class FindMaximumSubarrayTest {

    Integer[] array;
    Integer[] differenceArray;
    Class<Integer> type = Integer.class;
    Optional<FindMaximumSubarray.Answer<Integer>> answer;
    Comparator<Integer> comparator = Integer::compare;
    BinaryOperator<Integer> addition = (a, b) -> (a + b);
    BinaryOperator<Integer> subtraction = (a, b) -> (b - a);
    Integer infinity = Integer.MIN_VALUE;
    Integer zero = 0;

    @Before
    public void setUp() throws Exception {
        array = new Integer[]{100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97};
        differenceArray = ArrayUtils.generateIntervalsArray(array, type, subtraction);
    }

    @After
    public void tearDown() throws Exception {
        assert answer.isPresent();
        answer.ifPresent(a -> System.out.println(String.format("Max: %d, Head: %d, Tail: %d", a.getMax(), a.getHead(), a.getTail())));
    }

    @Test
    public void testBruteForce() throws Exception {
        answer = FindMaximumSubarray.bruteForce(array, subtraction, comparator, infinity);
    }

    @Test
    public void testDifferenceMethod() throws Exception {
        answer = FindMaximumSubarray.differenceMethod(differenceArray, addition, comparator, infinity, zero);
    }

    @Test
    public void testDivideAndConquer() throws Exception {
        answer = FindMaximumSubarray.divideAndConquer(differenceArray, addition, comparator, infinity, zero);
    }
}