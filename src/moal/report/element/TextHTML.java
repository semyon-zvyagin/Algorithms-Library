package moal.report.element;

public class TextHTML implements ElementHTML {

    private String text;
    private TextSize size;

    public TextHTML(String text) {
        this(text, TextSize.MEDIUM);
    }

    public TextHTML(String text, TextSize size) {
        this.text = text;
        this.size = size;
    }

    @Override
    public String getMarkup() {
        return size.getOpenTag() + text + size.getCloseTag();
    }

    public enum TextSize {
        SMALL, MEDIUM, LARGE;

        public static String getDefaultStyles() {

            return "<style>\n" +
                    "    h1 { font-size: small }\n" +
                    "    h2 { font-size: medium }\n" +
                    "    h3 { font-size: large }\n" +
                    "</style>\n";
        }

        String getOpenTag() {
            switch (this) {
                case SMALL:
                    return "<h1>";
                case MEDIUM:
                    return "<h2>";
                case LARGE:
                    return "<h3>";
            }
            return "";
        }

        String getCloseTag() {
            switch (this) {
                case SMALL:
                    return "</h1>";
                case MEDIUM:
                    return "</h2>";
                case LARGE:
                    return "</h3>";
            }
            return "";
        }
    }
}
