package moal.report.task.runner.engine;

import moal.report.task.boxing.TestingAlgorithmCase;

import java.util.*;
import java.util.function.IntUnaryOperator;

/**
 * Engine for testing tasks. First of all, adds tasks and
 * their names, after that start to perform, and result
 * it is time and complexity of task
 */
public class PerformEngine {

    private final int initialComplexity;
    private final int repeatCountCalculation;
    private final long borderLineOfCalculationTime;
    private final int borderLineOfUpComplexity;
    private final IntUnaryOperator functionToUpComplexity;
    private final LinkedList<TestingAlgorithmCase> tasks = new LinkedList<>();
    private final Map<String, LinkedList<Long>> results = new LinkedHashMap<>();
    private final HashSet<Integer> complexities = new HashSet<>();

    /**
     * First time conditions for  initialize
     *
     * @param initialComplexity           for first test
     * @param functionToUpComplexity      for up complexity
     * @param repeatCountCalculation      for times to do the task
     * @param borderLineOfCalculationTime border, witch task can process
     */
    public PerformEngine(int initialComplexity, IntUnaryOperator functionToUpComplexity, int repeatCountCalculation, long borderLineOfCalculationTime, int borderLineOfUpComplexity) {
        this.initialComplexity = initialComplexity;
        this.repeatCountCalculation = repeatCountCalculation;
        this.borderLineOfCalculationTime = borderLineOfCalculationTime;
        this.functionToUpComplexity = functionToUpComplexity;
        this.borderLineOfUpComplexity = borderLineOfUpComplexity;
    }

    /**
     * Just add one more task
     * @param task for calculation
     */
    public void addTask(TestingAlgorithmCase task) {
        tasks.add(task);
    }

    /**
     * Start perform machine, fill results
     */
    public void startPerform() {
        results.clear();

        for (TestingAlgorithmCase task : tasks) {
            int complexity = initialComplexity;
            int countOfUpComplexity = 0;
            final LinkedList<Long> measurements = new LinkedList<>();

            results.put(task.getName(), measurements);
            Long averageTime;
            do {
                Long time = 0L;

                for (int i = 0; i < repeatCountCalculation; i++) {
                    time += task.perform(complexity);
                }

                averageTime = time / repeatCountCalculation;
                measurements.add(averageTime);
                complexities.add(complexity);
                complexity = functionToUpComplexity.applyAsInt(complexity);
                countOfUpComplexity++;

                System.out.println(String.format("Task %s (complexity = %d) done for %d", task.getName(), complexity, averageTime));
            } while (averageTime < borderLineOfCalculationTime && countOfUpComplexity < borderLineOfUpComplexity);
        }
    }

    /**
     * @return results after testing
     */
    public Map<String, LinkedList<Long>> getResult() {
        return results;
    }

    /**
     * @return complexities after testing
     */
    public Set<Integer> getComplexities() {
        return complexities;
    }
}
