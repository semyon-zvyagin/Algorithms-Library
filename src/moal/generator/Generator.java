package moal.generator;

import com.sun.xml.internal.ws.util.StringUtils;
import moal.generator.entity.Person;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Need for simple generate some structure
 */
public class Generator {

    private static final char[] vowels = {'a','e','i','o','u'};
    private static final char[] consonants = {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z'};
    private static final int defaultAverageNumberOfSyllables = 2;
    private static Random random = new Random(System.currentTimeMillis());
    public static Predicate<Double> isHappened = (x) -> (x >= random.nextDouble());
    public static Supplier<Character> randomVowelSupplier = () -> (vowels[getRandomInteger(0, vowels.length)]);
    public static Supplier<Character> randomConsonantSupplier = () -> (consonants[getRandomInteger(0, consonants.length)]);
    private static double probabilityFirstConsonant = 0.7;
    private static double probabilitySecondConsonant = 0.4;

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

    /**
     * Return syllable like (v1,v2,v3) where
     * v1 is random consonant witch probability appear is {@link #probabilityFirstConsonant}
     * v2 is random vowels witch always appear
     * v1 is random consonant witch probability appear is {@link #probabilitySecondConsonant}
     * @return random syllable
     */
    public static String getRandomSyllable() {
        return  (isHappened.test(probabilityFirstConsonant) ? randomConsonantSupplier.get().toString() : "") +
                    randomVowelSupplier.get() +
                (isHappened.test(probabilitySecondConsonant) ? randomConsonantSupplier.get().toString() : "");
    }

    /**
     * Generate random name which contains at least two syllables
     * and average contains {@link #defaultAverageNumberOfSyllables}
     *
     * @return random name
     */
    public static String getRandomName() {
        return getRandomName(defaultAverageNumberOfSyllables);
    }

    /**
     * Generate random name which contains at least two syllables
     * and average contains {@code averageNumberOfSyllables}
     *
     * @param averageNumberOfSyllables - average count of syllables in generated name
     * @return random name
     */
    public static String getRandomName(int averageNumberOfSyllables) {
        int countOfSyllables = (int)Math.round(averageNumberOfSyllables * (1 + random.nextGaussian()));
        if (countOfSyllables < 2) {
            countOfSyllables = 2;
        }
        String name = Stream.generate(Generator::getRandomSyllable).limit(countOfSyllables).collect(Collectors.joining());
        return StringUtils.capitalize(name);
    }

    /**
     * Generate array with random {@link Person}s
     *
     * @param count of persons in array
     * @return random array of persons
     */
    public static Person[] getRandomPersonArray(int count) {
        return Stream.generate(Person::getRandom).limit(count).toArray(Person[]::new);
    }
}
