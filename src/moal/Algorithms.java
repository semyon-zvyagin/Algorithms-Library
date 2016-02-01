package moal;

import moal.array.FindMaximumSubarray;
import moal.array.FindTwoElementsTheSumOfWhichEqualsGivenNumber;
import moal.array.Sorting;
import moal.matrix.Multiplication;
import moal.report.task.boxing.cases.array.FindMaximumSubArrayAlgorithmCase;
import moal.report.task.boxing.cases.array.FindTwoElementsTheSumOfWhichEqualsGivenNumberAlgorithmCase;
import moal.report.task.boxing.cases.array.SortingAlgorithmCase;
import moal.report.task.boxing.cases.matrix.MultiplicationAlgorithmCase;
import moal.util.ArrayUtils;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class Algorithms {

    public static class ArraySorting {

        final static int k = 400;
        static final private Comparator<Integer> comparator = Integer::compare;
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
        static final private Comparator<Integer> comparator = Integer::compare;
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

        static FindMaximumSubArrayAlgorithmCase linearJayKadane = new FindMaximumSubArrayAlgorithmCase("Jay Kadane's") {
            @Override
            protected void compute() {
                Integer[] differenceArray = ArrayUtils.generateIntervalsArray(array, Integer.class, subtraction);
                FindMaximumSubarray.linearJayKadane(differenceArray, addition, comparator, zero);
            }
        };
    }

    public static class ArrayFindTwoElementsTheSumOfWhichEqualsGivenNumber {
        static final private BinaryOperator<Integer> addition = (a, b) -> (a + b);
        static final private BinaryOperator<Integer> subtraction = (a, b) -> (a - b);
        static final private Comparator<Integer> comparator = Integer::compare;

        static FindTwoElementsTheSumOfWhichEqualsGivenNumberAlgorithmCase bruteForce =
                new FindTwoElementsTheSumOfWhichEqualsGivenNumberAlgorithmCase("Brute Force") {
                    @Override
                    protected void compute() {
                        FindTwoElementsTheSumOfWhichEqualsGivenNumber.bruteForce(array, searchingSum, addition);
                    }
                };

        static FindTwoElementsTheSumOfWhichEqualsGivenNumberAlgorithmCase sortAndBinarySearch =
                new FindTwoElementsTheSumOfWhichEqualsGivenNumberAlgorithmCase("Sorting and Binary Search") {
                    @Override
                    protected void compute() {
                        FindTwoElementsTheSumOfWhichEqualsGivenNumber.sortAndBinarySearch(array, searchingSum, subtraction, comparator);
                    }
                };

        static FindTwoElementsTheSumOfWhichEqualsGivenNumberAlgorithmCase sortAndBinarySearchIgnoreDuplicates =
                new FindTwoElementsTheSumOfWhichEqualsGivenNumberAlgorithmCase("Sorting and Binary Search Ignore Duplicates") {
                    @Override
                    protected void compute() {
                        FindTwoElementsTheSumOfWhichEqualsGivenNumber.sortAndBinarySearchIgnoreDuplicates(array, searchingSum, subtraction, comparator);
                    }
                };

        static FindTwoElementsTheSumOfWhichEqualsGivenNumberAlgorithmCase hashSearch =
                new FindTwoElementsTheSumOfWhichEqualsGivenNumberAlgorithmCase("Hash Search") {
                    @Override
                    protected void compute() {
                        FindTwoElementsTheSumOfWhichEqualsGivenNumber.hashSearch(array, searchingSum, subtraction);
                    }
                };
    }

    public static class MatrixMultiplication {
        static final private Class<Integer> clazz = Integer.class;
        static final private Integer zero = 0;
        static final private BinaryOperator<Integer> addition = (a, b) -> (a + b);
        static final private BinaryOperator<Integer> subtraction = (a, b) -> (a - b);
        static final private BinaryOperator<Integer> multiplication = (a, b) -> (a * b);

        static MultiplicationAlgorithmCase simple =
                new MultiplicationAlgorithmCase("Simple") {
                    @Override
                    protected void compute() {
                        C = Multiplication.simple(clazz, A, B, addition, multiplication);
                    }
                };

        static MultiplicationAlgorithmCase divideAndConquer =
                new MultiplicationAlgorithmCase("Divide and Conquer") {
                    @Override
                    protected void compute() {
                        C = Multiplication.divideAndConquer(clazz, A, B, zero, addition, multiplication);
                    }
                };

        static MultiplicationAlgorithmCase strassen =
                new MultiplicationAlgorithmCase("Strassen") {
                    @Override
                    protected void compute() {
                        C = Multiplication.strassen(clazz, A, B, zero, addition, subtraction, multiplication);
                    }
                };
    }

}
