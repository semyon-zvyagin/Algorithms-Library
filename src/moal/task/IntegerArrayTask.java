package moal.task;

import moal.generator.Generator;

public abstract class IntegerArrayTask implements Task {
    protected Integer[] array;

    @Override
    public void prepare(int complexity) {
        array = Generator.getRandomIntegerArray(complexity, complexity);
    }
}
