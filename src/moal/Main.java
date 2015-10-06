package moal;

import moal.array.Sorting;
import moal.generator.Generator;
import moal.report.Report;
import moal.task.boxing.Starter;
import moal.task.boxing.TaskReturnTime;

import java.io.FileNotFoundException;
import java.util.Objects;

public class Main {

    public static void main(String... args) throws FileNotFoundException {

        Report report = new Report();

        int size = 128;
        Double[][] times = new Double[3][8];

        for (int count = 0; count < 8; count++) {
            size <<= 1;
            final Integer[] array = Generator.getRandomIntegerArray(size, size);
            TaskReturnTime bubble = new TaskReturnTime(() -> Sorting.bubble(array.clone(), (x, y) -> (Integer.compare(x, y))));
            TaskReturnTime insertion = new TaskReturnTime(() -> Sorting.insertion(array.clone(), (x, y) -> (Integer.compare(x, y))));

            times[0][count] = (double) size;

            Long time = Starter.startTask(bubble, 2, null);
            times[1][count] = Objects.isNull(time) ? -1 : (double) time;

            time = Starter.startTask(insertion, 2, null);
            times[2][count] = Objects.isNull(time) ? -1 : (double) time;

        }

        report.setRowNames(new String[]{"Count", "Bubble", "Insertion"});
        report.setResults(times);
        report.printHTML();
    }
}
