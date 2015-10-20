package moal;

import moal.array.Sorting;
import moal.generator.Generator;
import moal.report.Report;
import moal.task.TestingAlgorithmCase;
import moal.task.exception.NoneSuitableSolutionException;
import moal.task.runner.PerformEngine;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;

public class SortingAlgorithmReport {

    static final Comparator<Integer> comparator = (x, y) -> (Integer.compare(x, y));

    public static void main(String... args) throws FileNotFoundException, NoneSuitableSolutionException {

        Report report = new Report();
        PerformEngine engine = new PerformEngine(100, x -> (x << 1), 5, 100);

        engine.addTask("Bubble", new SortingAlgorithmCase() {
            @Override
            protected void compute() {
                Sorting.bubble(array, comparator);
            }
        });

        engine.addTask("Insertion", new SortingAlgorithmCase() {
            @Override
            protected void compute() {
                Sorting.insertion(array, comparator);
            }
        });

        engine.addTask("InsertionBinary", new SortingAlgorithmCase() {
            @Override
            protected void compute() {
                Sorting.insertionBinary(array, comparator);
            }
        });

        engine.addTask("Merge", new SortingAlgorithmCase() {
            @Override
            protected void compute() {
                Sorting.merge(array, comparator, Integer.MAX_VALUE);
            }
        });

        engine.addTask("Merge without Infinity", new SortingAlgorithmCase() {
            @Override
            protected void compute() {
                Sorting.merge(array, comparator);
            }
        });

        final int k = 400;
        engine.addTask(String.format("Merge without Infinity with Insertion while n < %d", k), new SortingAlgorithmCase() {
            @Override
            protected void compute() {
                Sorting.mergeWithInsertion(array, comparator, k);
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

    private static abstract class SortingAlgorithmCase extends TestingAlgorithmCase {
        protected Integer[] array;

        @Override
        protected void prepare(int complexity) {
            array = Generator.getRandomIntegerArray(complexity, complexity);
        }

        @Override
        protected boolean isSuitableSolution() {
            for (int i = 0; i < (array.length - 1); i++) {
                if (comparator.compare(array[i], array[i + 1]) == 1) {
                    return false;
                }
            }
            return true;
        }
    }
}
