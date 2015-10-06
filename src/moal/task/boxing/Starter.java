package moal.task.boxing;

import java.util.concurrent.*;

public class Starter {

    public static <T> T startTask(Callable<T> task, int timeBeforeInterrupted, T returnIfInterrupted) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<T> future = executor.submit(task);
        T result;

        try {
            result = future.get(timeBeforeInterrupted, TimeUnit.SECONDS);
        } catch (Exception e) {
            result = returnIfInterrupted;
        }

        future.cancel(true);
        executor.shutdownNow();

        return result;
    }

}
