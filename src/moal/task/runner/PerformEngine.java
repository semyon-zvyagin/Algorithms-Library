package moal.task.runner;

import moal.task.TestingAlgorithmCase;
import moal.task.exception.NoneSuitableSolutionException;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
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
    private final IntUnaryOperator functionToUpComplexity;
    private final Map<String, TestingAlgorithmCase> tasks = new LinkedHashMap<>();
    private final Map<String, LinkedList<Long>> results = new LinkedHashMap<>();

    /**
     * First time conditions for  initialize
     *
     * @param initialComplexity           for first test
     * @param functionToUpComplexity      for up complexity
     * @param repeatCountCalculation      for times to do the task
     * @param borderLineOfCalculationTime border, witch task can process
     */
    public PerformEngine(int initialComplexity, IntUnaryOperator functionToUpComplexity, int repeatCountCalculation, long borderLineOfCalculationTime) {
        this.initialComplexity = initialComplexity;
        this.repeatCountCalculation = repeatCountCalculation;
        this.borderLineOfCalculationTime = borderLineOfCalculationTime;
        this.functionToUpComplexity = functionToUpComplexity;
    }

    /**
     * Just add one more task
     * @param name of task
     * @param task for calculation
     */
    public void addTask(String name, TestingAlgorithmCase task) {
        tasks.put(name, task);
    }

    /**
     * Start perform machine, fill results
     */
    public void startPerform() throws NoneSuitableSolutionException {
        results.clear();

        for (String name : tasks.keySet()) {
            int complexity = initialComplexity;
            final TestingAlgorithmCase task = tasks.get(name);
            final LinkedList<Long> measurements = new LinkedList<>();

            results.put(name, measurements);
            Long averageTime;
            do {
                Long time = 0L;

                for (int i = 0; i < repeatCountCalculation; i++) {
                    time += task.perform(complexity);
                }

                averageTime = time / repeatCountCalculation;
                measurements.add(averageTime);
                complexity = functionToUpComplexity.applyAsInt(complexity);
            } while (averageTime < borderLineOfCalculationTime);
        }
    }

    /**
     * @return results after testing
     */
    public Map<String, LinkedList<Long>> getResult() {
        return results;
    }
}
