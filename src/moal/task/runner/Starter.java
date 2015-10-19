package moal.task.runner;

import java.util.concurrent.*;

/**
 * Static utils for run task
 */
public class Starter {

    /**
     * Run task, if working for task more then time limit, then
     * interrupt task.
     * <p>
     * WARNING: task must to process interrupt!
     *
     * @param task                  that need to start
     * @param timeBeforeInterrupted for waiting result, if not - interrupt
     * @param returnIfInterrupted   default value
     * @param <T>                   type of result
     * @return result of task or if interrupted - default value
     */
    public static <T> T startTask(Callable<T> task, int timeBeforeInterrupted, T returnIfInterrupted) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<T> future = executor.submit(task);
        T result;

        try {
            result = future.get(timeBeforeInterrupted, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            result = returnIfInterrupted;
        }

        future.cancel(true);
        executor.shutdownNow();

        return result;
    }

    /**
     * Just run task
     * @param task that need to start
     * @param returnIfInterrupted maybe interrupted by JVM
     * @param <T> type of return
     * @return result of task or if interrupted - default value
     */
    public static <T> T startTask(Callable<T> task, T returnIfInterrupted) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<T> future = executor.submit(task);
        T result;

        try {
            result = future.get();
        } catch (Exception e) {
            result = returnIfInterrupted;
        }

        future.cancel(true);
        executor.shutdownNow();

        return result;
    }

}
