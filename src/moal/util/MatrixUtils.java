package moal.util;

import java.lang.reflect.Array;

/**
 * Static utils for Matrix
 */
public class MatrixUtils {

    @SuppressWarnings("unchecked")
    public static <T> T[][] createMatrix(Class<T> clazz, int countStrings, int countColumns) {
        return (T[][]) Array.newInstance(clazz, countStrings, countColumns);
    }

    public static <T> T[][] fillMatrix(T[][] matrix, T element) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = element;
            }
        }

        return matrix;
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