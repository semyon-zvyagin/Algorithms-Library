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
        Double[][] times = new Double[6][10];

        for (int count = 0; count < 10; count++) {
            size <<= 1;
            final Integer[] array = Generator.getRandomIntegerArray(size, size);
            TaskReturnTime bubble = new TaskReturnTime(() -> Sorting.bubble(array.clone(), (x, y) -> (Integer.compare(x, y))));
            TaskReturnTime insertion = new TaskReturnTime(() -> Sorting.insertion(array.clone(), (x, y) -> (Integer.compare(x, y))));
            TaskReturnTime insertionBinary = new TaskReturnTime(() -> Sorting.insertionBinary(array.clone(), (x, y) -> (Integer.compare(x, y))));
            TaskReturnTime merge = new TaskReturnTime(() -> Sorting.merge(array.clone(), (x, y) -> (Integer.compare(x, y)), Integer.MAX_VALUE));
            TaskReturnTime mergeWithoutInfinity = new TaskReturnTime(() -> Sorting.merge(array.clone(), (x, y) -> (Integer.compare(x, y))));

            times[0][count] = (double) size;

            Long time = Starter.startTask(bubble, 1, null);
            times[1][count] = Objects.isNull(time) ? -1 : (double) time;

            time = Starter.startTask(insertion, 1, null);
            times[2][count] = Objects.isNull(time) ? -1 : (double) time;

            time = Starter.startTask(insertionBinary, 1, null);
            times[3][count] = Objects.isNull(time) ? -1 : (double) time;

            time = Starter.startTask(merge, 1, null);
            times[4][count] = Objects.isNull(time) ? -1 : (double) time;

            time = Starter.startTask(mergeWithoutInfinity, 1, null);
            times[5][count] = Objects.isNull(time) ? -1 : (double) time;

        }

        report.setRowNames(new String[]{"Count", "Bubble", "Insertion", "InsertionBinary", "Merge", "Merge without Infinity"});
        report.setResults(times);
        report.printHTML();
    }
}
