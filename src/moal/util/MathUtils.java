package moal.util;

public class MathUtils {

    public static double log(int x, int base) {
        return Math.log(x) / Math.log(base);
    }

    public static int nextPow2(int number) {
        return nextPow(number, 2);
    }

    public static int nextPow(int number, int exponent) {
        int base = 1;
        while (number > base) {
            base *= exponent;
        }
        return base;
    }
}
