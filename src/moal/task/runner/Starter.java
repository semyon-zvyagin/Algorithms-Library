package moal.task.runner;

import java.util.concurrent.*;

public class Starter {

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
