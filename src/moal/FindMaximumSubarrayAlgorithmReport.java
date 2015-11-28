package moal;

import moal.array.FindMaximumSubarray;
import moal.generator.Generator;
import moal.report.ReportHTML;
import moal.report.element.TableHTML;
import moal.report.element.TextHTML;
import moal.task.TestingAlgorithmCase;
import moal.task.boxing.TaskReturnTime;
import moal.task.exception.NoneSuitableSolutionException;
import moal.task.runner.PerformEngine;
import moal.util.ArrayUtils;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.BinaryOperator;

public class FindMaximumSubarrayAlgorithmReport {

    static final Comparator<Integer> comparator = (x, y) -> (Integer.compare(x, y));
    static final BinaryOperator<Integer> subtraction = (a, b) -> (a - b);
    static final BinaryOperator<Integer> addition = (a, b) -> (a + b);
    static final Integer infinity = Integer.MAX_VALUE;
    static final Integer zero = 0;

    public static void main(String... args) throws Exception {

        ReportHTML report = new ReportHTML("Find Maximum Subarray Algorithms");
        report.addElement(new TextHTML("Find Maximum Subarray Algorithms", TextHTML.TextSize.LARGE));

        PerformEngine engine = new PerformEngine(100, x -> (x << 1), 5, 100);

        engine.addTask("Brute Force", new FindMaximumSubArrayAlgorithmCase() {
            @Override
            protected void compute() {
                FindMaximumSubarray.bruteForce(array, subtraction, comparator, infinity);
            }
        });

        engine.addTask("Difference Method", new FindMaximumSubArrayAlgorithmCase() {
            @Override
            protected void compute() {
                Integer[] differenceArray = ArrayUtils.generateIntervalsArray(array, Integer.class, subtraction);
                FindMaximumSubarray.differenceMethod(differenceArray, addition, comparator, infinity, zero);
            }
        });

        engine.addTask("Divide And Rule", new FindMaximumSubArrayAlgorithmCase() {
            @Override
            protected void compute() {
                Integer[] differenceArray = ArrayUtils.generateIntervalsArray(array, Integer.class, subtraction);
                FindMaximumSubarray.divideAndRule(differenceArray, addition, comparator, infinity, zero);
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

    private static abstract class FindMaximumSubArrayAlgorithmCase extends TestingAlgorithmCase {
        protected Integer[] array;

        @Override
        protected void prepare(int complexity) {
            array = Generator.getRandomIntegerArray(complexity, complexity);
        }

        @Override
        protected boolean isSuitableSolution() {
            return true;
        }
    }
}
