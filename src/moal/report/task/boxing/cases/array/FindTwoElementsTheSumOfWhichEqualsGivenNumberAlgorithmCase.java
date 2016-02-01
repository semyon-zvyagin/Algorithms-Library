package moal.report.task.boxing.cases.array;

import moal.array.FindTwoElementsTheSumOfWhichEqualsGivenNumber;
import moal.generator.Generator;
import moal.report.task.boxing.TestingAlgorithmCase;

import java.util.Optional;

public abstract class FindTwoElementsTheSumOfWhichEqualsGivenNumberAlgorithmCase extends TestingAlgorithmCase {
    protected Integer[] array;
    protected Integer searchingSum;
    protected Optional<FindTwoElementsTheSumOfWhichEqualsGivenNumber.IndexAnswer> indexAnswer = Optional.empty();
    protected Optional<FindTwoElementsTheSumOfWhichEqualsGivenNumber.TypeAnswer> typeAnswer = Optional.empty();

    public FindTwoElementsTheSumOfWhichEqualsGivenNumberAlgorithmCase(String name) {
        super(name);
    }

    @Override
    protected void prepare(int complexity) {
        array = Generator.getRandomIntegerArray(complexity, complexity);
        Integer[] indices = Generator.getRandomIntegerArrayWithoutDuplicatesInRange(2, 0, complexity);
        searchingSum = array[indices[0]] + array[indices[1]];
    }

    @Override
    protected boolean isSuitableSolution() {
        if (indexAnswer.isPresent()) {
            return searchingSum == array[indexAnswer.get().getFirst()] + array[indexAnswer.get().getSecond()];
        }
        if (typeAnswer.isPresent()) {
            return searchingSum == (Integer) typeAnswer.get().getFirst() + (Integer) typeAnswer.get().getSecond();
        }
        return true;
    }
}
