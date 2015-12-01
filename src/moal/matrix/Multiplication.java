package moal.matrix;

import moal.util.MatrixUtils;

import java.util.function.BinaryOperator;

/**
 * Class with static methods for multiplication matrix
 */
public class Multiplication {

    /**
     * Simple Matrix multiplication two matrix:
     * C(n,m) = A(n,k) * B(k, m)
     * <p>
     * Complexity O(n * m * k), for square matrix O(n^3)
     *
     * @param clazz          of T type, for creation result matrix
     * @param A              matrix
     * @param B              matrix
     * @param addition       for addition T elements
     * @param multiplication for multiplication T elements
     * @param <T>            type of matrix elements
     * @return matrix C = A * B
     */
    public static <T> T[][] simple(Class<T> clazz, T[][] A, T[][] B, BinaryOperator<T> addition, BinaryOperator<T> multiplication) {

        int countRows = A.length;
        int countColumns = B[0].length;

        T[][] result = MatrixUtils.createMatrix(clazz, countRows, countColumns);

        int countTerms = B.length;
        for (int i = 0; i < countRows; i++) {
            for (int j = 0; j < countColumns; j++) {
                result[i][j] = multiplication.apply(A[i][0], B[0][j]);
                for (int k = 1; k < countTerms; k++) {
                    result[i][j] = addition.apply(result[i][j], multiplication.apply(A[i][k], B[k][j]));
                }
            }
        }

        return result;
    }
}
