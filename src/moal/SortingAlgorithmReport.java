package moal;

import moal.array.Sorting;
import moal.generator.Generator;
import moal.report.ReportHTML;
import moal.report.element.TableHTML;
import moal.report.element.TextHTML;
import moal.task.TestingAlgorithmCase;
import moal.task.boxing.TaskReturnTime;
import moal.task.exception.NoneSuitableSolutionException;
import moal.task.runner.PerformEngine;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;

public class SortingAlgorithmReport {

    static final Comparator<Integer> comparator = (x, y) -> (Integer.compare(x, y));

    public static void main(String... args) throws Exception {

        ReportHTML report = new ReportHTML("Sorting Methods");
        report.addElement(new TextHTML("Sorting Methods", TextHTML.TextSize.LARGE));

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

        TaskReturnTime startEngine = new TaskReturnTime(() -> {
            try {
                engine.startPerform();
            } catch (NoneSuitableSolutionException e) {
                e.printStackTrace();
            }
        });

        long time = startEngine.call();
        Map<String, LinkedList<Long>> result = engine.getResult();
        String[] algorithms = result.keySet().toArray(new String[result.size()]);

        TableHTML table = new TableHTML();

        for (String name : algorithms) {
            LinkedList<Long> measurements = result.get(name);
            table.addString(measurements.stream().map(x -> (x.doubleValue() / 1000)).toArray(Double[]::new));
        }
        table.addColumn(algorithms);

        report.addElement(table);
        report.addElement(new TextHTML(String.format("Compute for %f s.", (double) time / 1000), TextHTML.TextSize.SMALL));
        report.writeFile();
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
