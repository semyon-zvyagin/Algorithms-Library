package moal.report;

import moal.report.element.ElementHTML;
import moal.report.element.TextHTML;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ReportHTML {


    private static String[] HEAD = {"<!DOCTYPE html>", "<html>", "<head>",
            "<meta charset=\"utf-8\">",
            TextHTML.TextSize.getDefaultStyles(),
            "</head>", "<body>"};

    private static String[] TAIL = {"</body>", "</html>"};
    private String reportName;
    private List<ElementHTML> elements = new LinkedList<>();

    public ReportHTML() {
        this(new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()));
    }

    public ReportHTML(String reportName) {
        this.reportName = reportName;
    }

    public ReportHTML addElement(ElementHTML element) {
        elements.add(element);
        return this;
    }

    public void writeFile() {
        try (PrintWriter writer = new PrintWriter(reportName + ".html", "UTF-8")) {
            Arrays.stream(HEAD).forEachOrdered(writer::println);
            elements.stream().map(ElementHTML::getMarkup).forEachOrdered(writer::println);
            Arrays.stream(TAIL).forEachOrdered(writer::println);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
