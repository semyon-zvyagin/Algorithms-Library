package moal.report.task.boxing;

/**
 * This abstract class just a wrapper for testing Algorithm
 */
public abstract class TestingAlgorithmCase {

    private String name;

    public TestingAlgorithmCase(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Prepare test case for computing. Method must refresh task
     * based on complexity parameter. It is necessary to be able
     * to easily change the difficulty of the task for the test
     * algorithm.
     *
     * @param complexity a formal setting, you can understand it
     *                   like n in O(n) complexity
     */
    abstract protected void prepare(int complexity);

    /**
     * Computing task. Here need to realize Algorithm
     */
    abstract protected void compute();

    /**
     * It is check the results of computing. It performs after
     * that task will solved. It needs to notify that Algorithm
     * suitable solve task or not.
     * <p>
     * Return <tt>true</tt> if produced solution is expected or
     * <tt>false</tt> if unexpected.
     */
    abstract protected boolean isSuitableSolution();

    /**
     * Method prepare conditions of task based on complexity
     * parameter, after that solve task and measure compute time,
     * after that check result and return compute time or throw
     * exception that check failed.
     *
     * @param complexity use for {@link #prepare(int)} method
     * @return what time did {@link #compute()} method perform
     */
    public long perform(int complexity) {
        prepare(complexity);
        long start = System.currentTimeMillis();
        compute();
        long end = System.currentTimeMillis();

        if (isSuitableSolution()) {
            return end - start;
        } else {
            return -1;
        }
    }
}
