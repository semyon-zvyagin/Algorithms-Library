package moal;

import moal.array.FindMaximumSubarray;
import moal.array.Sorting;
import moal.report.task.boxing.cases.FindMaximumSubArrayAlgorithmCase;
import moal.report.task.boxing.cases.SortingAlgorithmCase;
import moal.util.ArrayUtils;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class Algorithms {

    public static class ArraySorting {

        final static int k = 400;
        static final private Comparator<Integer> comparator = (x, y) -> (Integer.compare(x, y));
        static final private Integer infinity = Integer.MAX_VALUE;
        static SortingAlgorithmCase bubble = new SortingAlgorithmCase("Bubble") {
            @Override
            protected void compute() {
                Sorting.bubble(array, comparator);
            }
        };
        static SortingAlgorithmCase insertion = new SortingAlgorithmCase("Insertion") {
            @Override
            protected void compute() {
                Sorting.insertion(array, comparator);
            }
        };
        static SortingAlgorithmCase binsertion = new SortingAlgorithmCase("InsertionBinary") {
            @Override
            protected void compute() {
                Sorting.insertionBinary(array, comparator);
            }
        };
        static SortingAlgorithmCase merge = new SortingAlgorithmCase("Merge") {
            @Override
            protected void compute() {
                Sorting.merge(array, comparator, infinity);
            }
        };
        static SortingAlgorithmCase mergeWithoutInfinity = new SortingAlgorithmCase("Merge without Infinity") {
            @Override
            protected void compute() {
                Sorting.merge(array, comparator);
            }
        };
        static SortingAlgorithmCase mergeWithInsertion = new SortingAlgorithmCase(String.format("Merge without Infinity with Insertion while n < %d", k)) {
            @Override
            protected void compute() {
                Sorting.mergeWithInsertion(array, comparator, k);
            }
        };
    }

    public static class ArrayFindMaxSubarray {
        static final private Comparator<Integer> comparator = (x, y) -> (Integer.compare(x, y));
        static final private BinaryOperator<Integer> subtraction = (a, b) -> (a - b);
        static final private BinaryOperator<Integer> addition = (a, b) -> (a + b);
        static final private Integer infinity = Integer.MAX_VALUE;
        static final private Integer zero = 0;

        static FindMaximumSubArrayAlgorithmCase bruteForce = new FindMaximumSubArrayAlgorithmCase("Brute Force") {
            @Override
            protected void compute() {
                FindMaximumSubarray.bruteForce(array, subtraction, comparator, infinity);
            }
        };

        static FindMaximumSubArrayAlgorithmCase difference = new FindMaximumSubArrayAlgorithmCase("Difference Method") {
            @Override
            protected void compute() {
                Integer[] differenceArray = ArrayUtils.generateIntervalsArray(array, Integer.class, subtraction);
                FindMaximumSubarray.differenceMethod(differenceArray, addition, comparator, infinity, zero);
            }
        };

        static FindMaximumSubArrayAlgorithmCase divideAndConquer = new FindMaximumSubArrayAlgorithmCase("Divide and Conquer") {
            @Override
            protected void compute() {
                Integer[] differenceArray = ArrayUtils.generateIntervalsArray(array, Integer.class, subtraction);
                FindMaximumSubarray.divideAndConquer(differenceArray, addition, comparator, infinity, zero);
            }
        };
    }

}
