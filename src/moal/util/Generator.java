package moal.util;

import java.util.Random;

public class Generator {

    private static Random random = new Random(System.currentTimeMillis());

    public static Integer[] getRandomIntegerArray(int count, int maxNumber) {
        return random.ints(0, maxNumber).limit(count).boxed().toArray(Integer[]::new);
    }
}
