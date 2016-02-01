package moal.matrix;

import moal.util.MathUtils;
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
        T[][] C = MatrixUtils.createMultiplicationResultMatrix(clazz, A, B);

        int countRows = A.length;
        int countColumns = B[0].length;
        int countTerms = B.length;

        for (int i = 0; i < countRows; i++) {
            for (int j = 0; j < countColumns; j++) {
                C[i][j] = multiplication.apply(A[i][0], B[0][j]);
                for (int k = 1; k < countTerms; k++) {
                    C[i][j] = addition.apply(C[i][j], multiplication.apply(A[i][k], B[k][j]));
                }
            }
        }

        return C;
    }

    private static <T> Task<T> toSquareTask(Class<T> clazz, T[][] A, T[][] B, T[][] C, T zero) {
        int maxCountA = Math.max(A.length, A[0].length);
        int maxCountB = Math.max(B.length, B[0].length);
        int maxCount = Math.max(maxCountA, maxCountB);
        int squareCount = MathUtils.nextPow2(maxCount);

        Matrix<T> squareA = Matrix.of(MatrixUtils.toSquareMatrix(clazz, A, zero, squareCount));
        Matrix<T> squareB = Matrix.of(MatrixUtils.toSquareMatrix(clazz, B, zero, squareCount));
        Matrix<T> squareC = Matrix.of(MatrixUtils.toSquareMatrix(clazz, C, zero, squareCount));

        return Task.of(squareA, squareB, squareC);
    }

    public static <T> T[][] divideAndConquer(Class<T> clazz, T[][] A, T[][] B, T zero, BinaryOperator<T> addition, BinaryOperator<T> multiplication) {
        T[][] C = MatrixUtils.createMultiplicationResultMatrix(clazz, A, B);
        Task<T> task = toSquareTask(clazz, A, B, C, zero);

        Matrix<T> result = squareMatrixMultiplyRecursive(clazz, task.A, task.B, addition, multiplication);

        MatrixUtils.copyByDestination(result.matrix, C);
        return C;
    }

    private static <T> Matrix<T> squareMatrixMultiplyRecursive(Class<T> clazz, Matrix<T> A, Matrix<T> B, BinaryOperator<T> addition, BinaryOperator<T> multiplication) {
        int n = A.rowEndIndex - A.rowStartIndex;
        Matrix<T> C = Matrix.of(MatrixUtils.create(clazz, n, n));
        if (n == 1) {
            C.matrix[C.rowStartIndex][C.columnStartIndex] = multiplication.apply(
                    A.matrix[A.rowStartIndex][A.columnStartIndex],
                    B.matrix[B.rowStartIndex][B.columnStartIndex]);
        } else {
            SquareSeparation<T> separatedA = SquareSeparation.of(A);
            SquareSeparation<T> separatedB = SquareSeparation.of(B);
            SquareSeparation<T> separatedC = SquareSeparation.of(C);

            separatedC.part11 = Matrix.addition(clazz, addition,
                    squareMatrixMultiplyRecursive(clazz, separatedA.part11, separatedB.part11, addition, multiplication),
                    squareMatrixMultiplyRecursive(clazz, separatedA.part12, separatedB.part21, addition, multiplication));

            separatedC.part12 = Matrix.addition(clazz, addition,
                    squareMatrixMultiplyRecursive(clazz, separatedA.part11, separatedB.part12, addition, multiplication),
                    squareMatrixMultiplyRecursive(clazz, separatedA.part12, separatedB.part22, addition, multiplication));

            separatedC.part21 = Matrix.addition(clazz, addition,
                    squareMatrixMultiplyRecursive(clazz, separatedA.part21, separatedB.part11, addition, multiplication),
                    squareMatrixMultiplyRecursive(clazz, separatedA.part22, separatedB.part21, addition, multiplication));

            separatedC.part22 = Matrix.addition(clazz, addition,
                    squareMatrixMultiplyRecursive(clazz, separatedA.part21, separatedB.part12, addition, multiplication),
                    squareMatrixMultiplyRecursive(clazz, separatedA.part22, separatedB.part22, addition, multiplication));

            separatedC.assigment(C);
        }

        return C;
    }

    private static class Matrix<T> {
        public final int rowStartIndex, rowEndIndex, columnStartIndex, columnEndIndex;
        public final T[][] matrix;

        private Matrix(T[][] matrix, int rowStartIndex, int rowEndIndex, int columnStartIndex, int columnEndIndex) {
            this.matrix = matrix;
            this.rowStartIndex = rowStartIndex;
            this.rowEndIndex = rowEndIndex;
            this.columnStartIndex = columnStartIndex;
            this.columnEndIndex = columnEndIndex;
        }

        @SuppressWarnings("unchecked")
        public static <T> Matrix<T> of(T[][] matrix) {
            return new Matrix(matrix, 0, matrix.length, 0, matrix[0].length);
        }

        @SuppressWarnings("unchecked")
        public static <T> Matrix<T> of(T[][] matrix, int rowStartIndex, int rowEndIndex, int columnStartIndex, int columnEndIndex) {
            return new Matrix(matrix, rowStartIndex, rowEndIndex, columnStartIndex, columnEndIndex);
        }

        public static <T> Matrix<T> addition(Class<T> clazz, BinaryOperator<T> addition, Matrix<T> A, Matrix<T> B) {
            int rowsCount = A.rowEndIndex - A.rowStartIndex;
            int columnsCount = A.columnEndIndex - A.columnStartIndex;

            Matrix<T> C = Matrix.of(MatrixUtils.create(clazz, rowsCount, columnsCount));
            for (int i = 0; i < rowsCount; i++) {
                for (int j = 0; j < columnsCount; j++) {
                    C.matrix[C.rowStartIndex + i][C.columnStartIndex + j] =
                            addition.apply(
                                    A.matrix[A.rowStartIndex + i][A.columnStartIndex + j],
                                    B.matrix[B.rowStartIndex + i][B.columnStartIndex + j]);
                }
            }

            return C;
        }
    }

    private static class Task<T> {
        public Matrix<T> A, B, C;

        private Task(Matrix<T> A, Matrix<T> B, Matrix<T> C) {
            this.A = A;
            this.B = B;
            this.C = C;
        }

        @SuppressWarnings("unchecked")
        public static <T> Task<T> of(Matrix<T> A, Matrix<T> B, Matrix<T> C) {
            return new Task(A, B, C);
        }
    }

    private static class SquareSeparation<T> {
        public Matrix<T> part11, part12, part21, part22;

        private SquareSeparation(Matrix<T> part11, Matrix<T> part12, Matrix<T> part21, Matrix<T> part22) {
            this.part11 = part11;
            this.part12 = part12;
            this.part21 = part21;
            this.part22 = part22;
        }

        @SuppressWarnings("unchecked")
        public static <T> SquareSeparation<T> of(Matrix<T> M) {
            int halfSize = (M.rowEndIndex - M.rowStartIndex) / 2;
            int middleRow = M.rowStartIndex + halfSize;
            int middleColumn = M.columnStartIndex + halfSize;
            return new SquareSeparation(
                    Matrix.of(M.matrix, M.rowStartIndex, middleRow, M.columnStartIndex, middleColumn),
                    Matrix.of(M.matrix, M.rowStartIndex, middleRow, middleColumn, M.columnEndIndex),
                    Matrix.of(M.matrix, middleRow, M.rowEndIndex, M.columnStartIndex, middleColumn),
                    Matrix.of(M.matrix, middleRow, M.rowEndIndex, middleColumn, M.columnEndIndex));
        }

        public void assigment(Matrix<T> C) {
            int halfSize = (C.rowEndIndex - C.columnStartIndex) / 2;
            int middleRow = C.rowStartIndex + halfSize;
            int middleColumn = C.columnStartIndex + halfSize;
            for (int i = 0; i < halfSize; i++) {
                for (int j = 0; j < halfSize; j++) {
                    C.matrix[C.rowStartIndex + i][C.columnStartIndex + j] = part11.matrix[part11.rowStartIndex + i][part11.columnStartIndex + j];
                    C.matrix[C.rowStartIndex + i][middleColumn + j] = part12.matrix[part12.rowStartIndex + i][part12.columnStartIndex + j];
                    C.matrix[middleRow + i][C.columnStartIndex + j] = part21.matrix[part21.rowStartIndex + i][part21.columnStartIndex + j];
                    C.matrix[middleRow + i][middleColumn + j] = part22.matrix[part22.rowStartIndex + i][part22.columnStartIndex + j];
                }
            }
        }
    }
}
