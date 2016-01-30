package moal.generator;

import org.junit.Test;

import java.util.Arrays;

public class GeneratorTest {

    @Test
    public void testGetRandomIntegerArrayWithoutDuplicatesInRange() throws Exception {
        System.out.print(Arrays.toString(Generator.getRandomIntegerArrayWithoutDuplicatesInRange(2, 0, 2)));
    }
}