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

        String getOpenTag() {
            switch (this) {
                case SMALL:
                    return "<h3>";
                case MEDIUM:
                    return "<h2>";
                case LARGE:
                    return "<h1>";
            }
            return "";
        }

        String getCloseTag() {
            switch (this) {
                case SMALL:
                    return "</h3>";
                case MEDIUM:
                    return "</h2>";
                case LARGE:
                    return "</h1>";
            }
            return "";
        }
    }
}
