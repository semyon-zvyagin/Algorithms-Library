package moal;

import moal.report.ReportElementsGeneratorHTML;
import moal.report.task.boxing.TestingAlgorithmCase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class AlgorithmReport {

    public static void refreshArraySorting(Document document) {

        TestingAlgorithmCase[] algorithms = {
                Algorithms.ArraySorting.bubble,
                Algorithms.ArraySorting.insertion,
                Algorithms.ArraySorting.binsertion,
                Algorithms.ArraySorting.merge,
                Algorithms.ArraySorting.mergeWithoutInfinity,
                Algorithms.ArraySorting.mergeWithInsertion
        };

        String html = ReportElementsGeneratorHTML.tablePerformance("Sorting Array", 100, x -> x << 1, 5, 100, 16, algorithms);

        Element div = document.select("div#arraySorting").first();
        div.html(html);
    }

    public static void refreshArrayFindTwoElementsTheSumOfWhichEqualsGivenNumber(Document document) {

        TestingAlgorithmCase[] algorithms = {
                Algorithms.ArrayFindTwoElementsTheSumOfWhichEqualsGivenNumber.bruteForce,
                Algorithms.ArrayFindTwoElementsTheSumOfWhichEqualsGivenNumber.sortAndBinarySearch,
                Algorithms.ArrayFindTwoElementsTheSumOfWhichEqualsGivenNumber.sortAndBinarySearchIgnoreDuplicates,
                Algorithms.ArrayFindTwoElementsTheSumOfWhichEqualsGivenNumber.hashSearch
        };

        String html = ReportElementsGeneratorHTML.tablePerformance("Find Two Elements The Sum Of Which Equals Given Number", 100, x -> x << 1, 5, 100, 16, algorithms);

        Element div = document.select("div#arrayFindTwoElementsTheSumOfWhichEqualsGivenNumber").first();
        div.html(html);
    }

    public static void refreshArrayFindMaxSubarray(Document document) {

        TestingAlgorithmCase[] algorithms = {
                Algorithms.ArrayFindMaxSubarray.bruteForce,
                Algorithms.ArrayFindMaxSubarray.difference,
                Algorithms.ArrayFindMaxSubarray.divideAndConquer,
                Algorithms.ArrayFindMaxSubarray.linearJayKadane
        };

        String html = ReportElementsGeneratorHTML.tablePerformance("Find Maximal Subarray", 100, x -> x << 1, 5, 100, 16, algorithms);

        Element div = document.select("div#arrayFindMaximumSubarray").first();
        div.html(html);
    }

    public static void refreshMatrixMultiplication(Document document) {

        TestingAlgorithmCase[] algorithms = {
                Algorithms.MatrixMultiplication.simple,
                Algorithms.MatrixMultiplication.divideAndConquer,
                Algorithms.MatrixMultiplication.strassen
        };

        String html1 = ReportElementsGeneratorHTML.tablePerformance("Matrix Multiplication", 10, x -> x << 1, 3, 1000, 16, algorithms);
        String html2 = ReportElementsGeneratorHTML.tablePerformance("<h2>    squared matrix with edge = 2 ^ n</h2>", 16, x -> x << 1, 3, 1000, 16, algorithms);

        Element div = document.select("div#matrixMultiplication").first();
        div.html(html1 + html2);
    }

    public static void main(String... args) throws Exception {

        File file = new File("Algorithms Report.html");
        Document document = Jsoup.parse(file, "UTF-8");

        //refreshArraySorting(document);
        //refreshArrayFindTwoElementsTheSumOfWhichEqualsGivenNumber(document);
        //refreshArrayFindMaxSubarray(document);
        refreshMatrixMultiplication(document);

        try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
            writer.print(document.html());
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
