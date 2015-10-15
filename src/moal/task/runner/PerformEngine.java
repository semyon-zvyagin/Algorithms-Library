package moal.task.runner;

import moal.task.Task;
import moal.task.boxing.TaskReturnTime;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.IntUnaryOperator;

public class PerformEngine {

    private final int initialComplexity;
    private final int repeatCountCalculation;
    private final long borderLineOfCalculationTime;
    private final IntUnaryOperator functionToUpComplexity;
    private final Map<String, Task> tasks = new LinkedHashMap<>();
    private final Map<String, LinkedList<Long>> results = new LinkedHashMap<>();

    public PerformEngine(int initialComplexity, IntUnaryOperator functionToUpComplexity, int repeatCountCalculation, long borderLineOfCalculationTime) {
        this.initialComplexity = initialComplexity;
        this.repeatCountCalculation = repeatCountCalculation;
        this.borderLineOfCalculationTime = borderLineOfCalculationTime;
        this.functionToUpComplexity = functionToUpComplexity;
    }

    public void addTask(String name, Task task) {
        tasks.put(name, task);
    }

    public void startPerform() {
        results.clear();

        for (String name : tasks.keySet()) {
            int complexity = initialComplexity;
            final Task task = tasks.get(name);
            final LinkedList<Long> measurements = new LinkedList<>();

            results.put(name, measurements);
            Long averageTime;
            do {
                Long time = 0L;
                for (int i = 0; i < repeatCountCalculation; i++) {
                    task.prepare(complexity);
                    time += Starter.startTask(new TaskReturnTime(task::calculate), null);
                }

                averageTime = time / repeatCountCalculation;
                measurements.add(averageTime);
                complexity = functionToUpComplexity.applyAsInt(complexity);
            } while (averageTime < borderLineOfCalculationTime);
        }
    }

    public Map<String, LinkedList<Long>> getResult() {
        return results;
    }
}
