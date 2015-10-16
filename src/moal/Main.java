package moal;

import moal.array.Sorting;
import moal.report.Report;
import moal.task.IntegerArrayTask;
import moal.task.runner.PerformEngine;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Map;

public class Main {

    public static void main(String... args) throws FileNotFoundException {

        Report report = new Report();
        PerformEngine engine = new PerformEngine(100, x -> (x << 1), 5, 100);

        engine.addTask("Bubble", new IntegerArrayTask() {
            @Override
            public void calculate() {
                Sorting.bubble(array, (x, y) -> (Integer.compare(x, y)));
            }
        });

        engine.addTask("Insertion", new IntegerArrayTask() {
            @Override
            public void calculate() {
                Sorting.insertion(array, (x, y) -> (Integer.compare(x, y)));
            }
        });

        engine.addTask("InsertionBinary", new IntegerArrayTask() {
            @Override
            public void calculate() {
                Sorting.insertionBinary(array, (x, y) -> (Integer.compare(x, y)));
            }
        });

        engine.addTask("Merge", new IntegerArrayTask() {
            @Override
            public void calculate() {
                Sorting.merge(array, (x, y) -> (Integer.compare(x, y)), Integer.MAX_VALUE);
            }
        });

        engine.addTask("Merge without Infinity", new IntegerArrayTask() {
            @Override
            public void calculate() {
                Sorting.merge(array, (x, y) -> (Integer.compare(x, y)));
            }
        });

        final int k = 400;
        engine.addTask(String.format("Merge without Infinity with Insertion while n < %d", k), new IntegerArrayTask() {
            @Override
            public void calculate() {
                Sorting.mergeWithInsertion(array, (x, y) -> (Integer.compare(x, y)), k);
            }
        });

        engine.startPerform();

        Map<String, LinkedList<Long>> result = engine.getResult();
        String[] names = result.keySet().toArray(new String[result.size()]);
        Double[][] times = new Double[result.size()][];

        int i = 0;
        for (String name : names) {
            LinkedList<Long> measurements = result.get(name);
            times[i] = measurements.stream().map(x -> (x.doubleValue() / 1000)).toArray(Double[]::new);
            i++;
        }

        report.setRowNames(names);
        report.setResults(times);
        report.printHTML();
    }
}
