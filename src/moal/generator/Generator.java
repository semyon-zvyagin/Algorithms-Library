package moal.generator;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Need for simple generate some structure
 */
public class Generator {

    private static Random random = new Random(System.currentTimeMillis());

    /**
     * Generate random integer between two numbers.
     *
     * @param leftInclusive  in range (must be less rightExclusive)
     * @param rightExclusive in range
     * @return random integer in the range [leftInclusive .. rightExclusive)
     */
    public static Integer getRandomInteger(int leftInclusive, int rightExclusive) {
        return leftInclusive + random.nextInt(rightExclusive - leftInclusive);
    }

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
     * Generate random integer array of different numbers in range.
     *
     * @param count          length of returned array
     * @param leftInclusive  in range (must be less rightExclusive)
     * @param rightExclusive in range
     * @return random integer array of different number in range [leftInclusive .. rightExclusive)
     */
    public static Integer[] getRandomIntegerArrayWithoutDuplicatesInRange(int count, int leftInclusive, int rightExclusive) {
        if (rightExclusive - leftInclusive < count) {
            throw new IllegalArgumentException(String.format("Range %d less then count %d", rightExclusive - leftInclusive, count));
        }
        List<Integer> range = IntStream.range(leftInclusive, rightExclusive).boxed().collect(Collectors.toList());
        Collections.shuffle(range);
        return range.stream().limit(count).toArray(Integer[]::new);
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
