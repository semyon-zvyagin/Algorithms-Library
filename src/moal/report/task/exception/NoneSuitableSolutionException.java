package moal.report.task.exception;

/**
 * It throw when produced solution after computing is
 * none suitable.
 */
public class NoneSuitableSolutionException extends Exception {

    public NoneSuitableSolutionException(String msg) {
        super(msg);
    }

    public NoneSuitableSolutionException() {
    }
}
