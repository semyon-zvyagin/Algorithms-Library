package moal.generator;

import org.junit.Test;

import java.util.Arrays;

public class GeneratorTest {
    @Test
    public void getRandomName() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(Generator.getRandomName(2));
        }
    }

    @Test
    public void testGetRandomIntegerArrayWithoutDuplicatesInRange() throws Exception {
        System.out.print(Arrays.toString(Generator.getRandomIntegerArrayWithoutDuplicatesInRange(2, 0, 2)));
    }
}