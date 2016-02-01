package moal.report.task.boxing.cases.array;

import moal.generator.Generator;
import moal.report.task.boxing.TestingAlgorithmCase;

public abstract class SortingAlgorithmCase extends TestingAlgorithmCase {

    protected Integer[] array;

    public SortingAlgorithmCase(String name) {
        super(name);
    }

    @Override
    protected void prepare(int complexity) {
        array = Generator.getRandomIntegerArray(complexity, complexity);
    }

    @Override
    protected boolean isSuitableSolution() {
        for (int i = 0; i < (array.length - 1); i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
