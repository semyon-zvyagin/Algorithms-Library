package moal.report.task.boxing.cases.matrix;

import moal.generator.Generator;
import moal.report.task.boxing.TestingAlgorithmCase;

public abstract class MultiplicationAlgorithmCase extends TestingAlgorithmCase {

    protected Integer[][] A, B, C;

    public MultiplicationAlgorithmCase(String name) {
        super(name);
    }

    @Override
    protected void prepare(int complexity) {
        A = Generator.getRandomIntegerMatrix(complexity, complexity, 100);
        B = Generator.getRandomIntegerMatrix(complexity, complexity, 100);
    }

    @Override
    protected boolean isSuitableSolution() {
        int randomRow = Generator.getRandomInteger(0, A.length);
        int randomColumn = Generator.getRandomInteger(0, B[0].length);
        int sum = 0;

        for (int i = 0; i < B.length; i++) {
            sum += A[randomRow][i] * B[i][randomColumn];
        }

        return C[randomRow][randomColumn] == sum;
    }
}
