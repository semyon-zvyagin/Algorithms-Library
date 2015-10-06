package moal.task.boxing;

import java.util.concurrent.Callable;

public class TaskReturnTime implements Callable<Long> {

    private long start, end;
    private Runnable task;

    public TaskReturnTime(Runnable task) {
        this.task = task;
    }

    @Override
    public Long call() throws Exception {

        start = System.currentTimeMillis();
        task.run();
        end = System.currentTimeMillis();

        return end - start;
    }
}
