package moal.generator;

import java.util.Random;

/**
 * Need for simple generate some structure
 */
public class Generator {

    private static Random random = new Random(System.currentTimeMillis());

    /**
     * Generate random integer array.
     *
     * @param count     length of returned array
     * @param maxNumber will generate integer number between [0..maxNumber]
     * @return random array with defining parameters
     */
    public static Integer[] getRandomIntegerArray(int count, int maxNumber) {
        return random.ints(0, maxNumber).limit(count).boxed().toArray(Integer[]::new);
    }

    /**
     * Generate random integer matrix.
     *
     * @param countRows    count elements in row
     * @param countColumns count elements in column
     * @param maxNumber    will generate integer number between [0..maxNumber]
     * @return random matrix with defining parameters
     */
    public static Integer[][] getRandomIntegerMatrix(int countRows, int countColumns, int maxNumber) {
        Integer[][] result = new Integer[countRows][];

        for (int i = 0; i < countRows; i++) {
            result[i] = getRandomIntegerArray(countColumns, maxNumber);
        }

        return result;
    }
}
