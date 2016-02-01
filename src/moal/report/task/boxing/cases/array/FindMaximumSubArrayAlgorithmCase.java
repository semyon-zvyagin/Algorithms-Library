package moal.report.task.boxing.cases.array;

import moal.generator.Generator;
import moal.report.task.boxing.TestingAlgorithmCase;

public abstract class FindMaximumSubArrayAlgorithmCase extends TestingAlgorithmCase {

    protected Integer[] array;

    public FindMaximumSubArrayAlgorithmCase(String name) {
        super(name);
    }

    @Override
    protected void prepare(int complexity) {
        array = Generator.getRandomIntegerArray(complexity, complexity);
    }

    @Override
    protected boolean isSuitableSolution() {
        return true;
    }
}
