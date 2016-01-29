package moal.array;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class FindTwoElementsTheSumOfWhichEqualsGivenNumberTest {

    Integer[] array;
    Integer givenNumber;
    BinaryOperator<Integer> addition = (a, b) -> (a + b);
    BinaryOperator<Integer> subtraction = (a, b) -> (a - b);
    Comparator<Integer> comparator = Integer::compare;
    Optional<FindTwoElementsTheSumOfWhichEqualsGivenNumber.IndexAnswer> indexAnswer = Optional.empty();
    Optional<FindTwoElementsTheSumOfWhichEqualsGivenNumber.TypeAnswer> typeAnswer = Optional.empty();

    @Before
    public void setUp() throws Exception {
        array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -2, -3, -4, -5, -6, -7, -8, -9};
        givenNumber = -8;
    }

    @After
    public void tearDown() throws Exception {
        assert indexAnswer.isPresent() || typeAnswer.isPresent();

        indexAnswer.ifPresent(a -> System.out.println(String.format("A[%d] = %d, A[%d] = %d, Sum = %d",
                a.getFirst(), array[a.getFirst()],
                a.getSecond(), array[a.getSecond()], givenNumber)));

        typeAnswer.ifPresent(a -> System.out.println(String.format("Sum of < %d, %d > is %d",
                a.getFirst(), a.getSecond(), givenNumber)));
    }

    @Test
    public void testBruteForce() throws Exception {
        indexAnswer = FindTwoElementsTheSumOfWhichEqualsGivenNumber.bruteForce(array, givenNumber, addition);
    }

    @Test
    public void testSortAndBinarySearch() throws Exception {
        indexAnswer = FindTwoElementsTheSumOfWhichEqualsGivenNumber.sortAndBinarySearch(array, givenNumber, subtraction, comparator);
    }

    @Test
    public void testSortAndBinarySearchIgnoreDuplicates() throws Exception {
        indexAnswer = FindTwoElementsTheSumOfWhichEqualsGivenNumber.sortAndBinarySearchIgnoreDuplicates(array, givenNumber, subtraction, comparator);
    }

    @Test
    public void testHashSearch() throws Exception {
        typeAnswer = FindTwoElementsTheSumOfWhichEqualsGivenNumber.hashSearch(array, givenNumber, subtraction, comparator);
    }
}