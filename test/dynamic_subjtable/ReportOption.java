package dynamic_subjtable;

/**
 *
 * @author Raven
 */
public class ReportOption {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object[] getOptions() {
        return options;
    }

    public void setOptions(Object[] options) {
        this.options = options;
    }

    public ReportOption(String title, Object... options) {
        this.title = title;
        this.options = options;
    }
    private String title;
    private Object options[];
}
