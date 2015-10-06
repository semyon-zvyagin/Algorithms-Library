package moal.report;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Report {

    private Double[][] results;
    private String[] rowNames;


    public Report() {
    }

    public void setResults(Double[][] results) {
        this.results = results;
    }

    public void setRowNames(String[] rowNames) {
        this.rowNames = rowNames;
    }

    public void printHTML() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("report.html");
        writer.print("<!DOCTYPE html>\n" +
                "<html>\n" +
                " <head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <title>Report</title>\n" +
                " </head>\n" +
                " <body>\n" +
                "  <table border=\"1\">\n" +
                "   <tr>\n");

        for (int i = 0; i < results.length; i++) {
            writer.print("   <tr>\n");
            writer.print("    <th>" + rowNames[i] + "</th>\n");
            for (Double result : results[i]) {
                writer.print("    <th>" + result.toString() + "</th>\n");
            }
            writer.print("   </tr>\n");
        }

        writer.print(" </table>\n" +
                " </body>\n" +
                "</html>");
        writer.close();
    }
}
