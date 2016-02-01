package moal.util;

import java.lang.reflect.Array;

/**
 * Static utils for Matrix
 */
public class MatrixUtils {

    @SuppressWarnings("unchecked")
    public static <T> T[][] create(Class<T> clazz, int countRows, int countColumns) {
        return (T[][]) Array.newInstance(clazz, countRows, countColumns);
    }

    public static <T> T[][] createMultiplicationResultMatrix(Class<T> clazz, T[][] A, T[][] B) {
        int countRows = A.length;
        int countColumns = B[0].length;
        return MatrixUtils.create(clazz, countRows, countColumns);
    }

    public static <T> void fill(T[][] matrix, T element) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = element;
            }
        }
    }

    public static <T> void copyBySource(T[][] source, T[][] destination) {
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[i].length; j++) {
                destination[i][j] = source[i][j];
            }
        }
    }

    public static <T> void copyByDestination(T[][] source, T[][] destination) {
        for (int i = 0; i < destination.length; i++) {
            for (int j = 0; j < destination[i].length; j++) {
                destination[i][j] = source[i][j];
            }
        }
    }

    public static <T> T[][] toSquareMatrix(Class<T> clazz, T[][] source, T fillElement, int countRowsAndColumns) {
        int countRows = source.length;
        int countColumns = source[0].length;

        if ((countRows == countColumns) && (countRows == countRowsAndColumns))
            return source;

        T[][] result = create(clazz, countRowsAndColumns, countRowsAndColumns);
        fill(result, fillElement);
        copyBySource(source, result);
        return result;
    }

    public static <T> String getString(T[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j].toString());
                sb.append('\t');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}