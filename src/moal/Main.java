package moal;

import moal.array.Sorting;
import moal.report.Report;
import moal.util.Generator;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String... args) throws FileNotFoundException {

        Report report = new Report();
        long start, end;
        Integer[] array, arrayForTest;

        String[] rowNames = new String[8];
        int size = 128;
        Double[][] times = new Double[3][8];

        System.out.println("1");
        for (int count = 0; count < 8; count++) {
            size <<= 1;
            array = Generator.getRandomIntegerArray(size, size);
            arrayForTest = array.clone();

            times[0][count] = (double) size;

            start = System.currentTimeMillis();
            Sorting.bubble(arrayForTest, (x, y) -> (Integer.compare(x, y)));
            end = System.currentTimeMillis();
            times[1][count] = (double) (end - start) / 1000;

            arrayForTest = array.clone();

            start = System.currentTimeMillis();
            Sorting.insertion(arrayForTest, (x, y) -> (Integer.compare(x, y)));
            end = System.currentTimeMillis();
            times[2][count] = (double) (end - start) / 1000;

        }
        System.out.println("1");

        report.setRowNames(new String[]{"Count", "Bubble", "Insertion"});
        report.setResults(times);
        report.printHTML();
    }
}
