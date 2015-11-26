package moal.report.element;

import moal.util.ArrayUtils;

import java.util.LinkedList;
import java.util.List;

public class TableHTML implements ElementHTML {

    private List<List<String>> table = new LinkedList<>();

    public TableHTML addString(Object[] newString) {
        return addString(ArrayUtils.convertToStringArray(newString));
    }

    public TableHTML addString(String[] newString) {
        table.add(ArrayUtils.convertToLinkedList(newString));
        return this;
    }

    public TableHTML addColumn(Object[] newColumn) {
        return addColumn(ArrayUtils.convertToStringArray(newColumn));
    }

    public TableHTML addColumn(String[] newColumn) {
        for (int i = newColumn.length; i > table.size(); i--) {
            table.add(new LinkedList<>());
        }

        for (int i = 0; i < newColumn.length; i++) {
            table.get(i).add(newColumn[i]);
        }

        return this;
    }

    @Override
    public String getMarkup() {
        StringBuilder sb = new StringBuilder();
        sb.append("<table>\n");
        for (List<String> strings : table) {
            sb.append("    <tr>");
            for (String string : strings) {
                sb.append("<td>");
                sb.append(string);
                sb.append("</td>");
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>");
        return sb.toString();
    }
}