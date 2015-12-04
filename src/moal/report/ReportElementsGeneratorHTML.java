package moal.report;

import moal.report.element.ElementHTML;
import moal.report.element.TableHTML;
import moal.report.element.TextHTML;
import moal.report.task.boxing.TaskReturnTime;
import moal.report.task.boxing.TestingAlgorithmCase;
import moal.report.task.runner.Starter;
import moal.report.task.runner.engine.PerformEngine;

import java.util.LinkedList;
import java.util.Map;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportElementsGeneratorHTML {

    public static String tablePerformance(String name, int initialComplexity, IntUnaryOperator functionToUpComplexity, int repeatCountCalculation, long borderLineOfCalculationTime, TestingAlgorithmCase... cases) {
        LinkedList<ElementHTML> elements = new LinkedList<>();
        elements.add(new TextHTML(name, TextHTML.TextSize.LARGE));

        PerformEngine engine = new PerformEngine(initialComplexity, functionToUpComplexity, repeatCountCalculation, borderLineOfCalculationTime);

        Stream.of(cases).forEachOrdered(engine::addTask);
        long time = Starter.startTask(new TaskReturnTime(engine::startPerform), -1L);

        Map<String, LinkedList<Long>> result = engine.getResult();
        String[] algorithms = result.keySet().toArray(new String[result.size()]);

        TableHTML table = new TableHTML();
        for (String algorithm : algorithms) {
            LinkedList<Long> measurements = result.get(algorithm);
            table.addString(measurements.stream().map(x -> (x.doubleValue() / 1000)).toArray(Double[]::new));
        }

        table.addColumnToBeginning(algorithms);
        String[] complexities = Stream.concat(Stream.of("Names\\Complexities"), engine.getComplexities().stream().sorted().map(Object::toString)).toArray(String[]::new);
        table.addStringToBeginning(complexities);
        elements.add(table);

        elements.add(new TextHTML(String.format("Compute for %f s.", (double) time / 1000), TextHTML.TextSize.SMALL));
        return elements.stream().map(ElementHTML::getMarkup).collect(Collectors.joining("\n"));
    }

}
