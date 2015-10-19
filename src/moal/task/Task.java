package moal.task;

/**
 * For tasks with upper complexity
 */
public interface Task {
    /**
     * Prepare task with define complexity
     *
     * @param complexity of task
     */
    void prepare(int complexity);

    /**
     * Solve task
     */
    void calculate();
}
