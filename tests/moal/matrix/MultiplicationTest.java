package moal.matrix;

import moal.generator.Generator;
import moal.util.MatrixUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.function.BinaryOperator;

public class MultiplicationTest {

    Integer[][] A, B, C;
    int maxElement = 10;
    int maxCountStrings = 5;

    BinaryOperator<Integer> addition = (a, b) -> (a + b);
    BinaryOperator<Integer> subtraction = (a, b) -> (a - b);
    BinaryOperator<Integer> multiplication = (a, b) -> (b * a);
    Integer zero = 0;

    @Before
    public void setUp() throws Exception {
        int countTerms = Generator.getRandomInteger(1, maxCountStrings);
        int countRowsA = Generator.getRandomInteger(1, maxCountStrings);
        int countColumnsB = Generator.getRandomInteger(1, maxCountStrings);
        A = Generator.getRandomIntegerMatrix(countRowsA, countTerms, maxElement);
        B = Generator.getRandomIntegerMatrix(countTerms, countColumnsB, maxElement);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println(MatrixUtils.getString(A));
        System.out.println(MatrixUtils.getString(B));
        System.out.println(MatrixUtils.getString(C));
    }

    @Test
    public void testSimple() throws Exception {
        C = Multiplication.simple(Integer.class, A, B, addition, multiplication);
    }

    @Test
    public void testDivideAndConquer() throws Exception {
        C = Multiplication.divideAndConquer(Integer.class, A, B, zero, addition, multiplication);
    }

    @Test
    public void testStrassen() throws Exception {
        C = Multiplication.strassen(Integer.class, A, B, zero, addition, subtraction, multiplication);
    }
}