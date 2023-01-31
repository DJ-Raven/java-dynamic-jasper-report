package raven.dynamicjasper.template;

/**
 *
 * @author Raven
 */
public class Label {

    public static enum Type {
        STRING, NUMBER, IMAGE
    }

    public String getLabelTitle() {
        return labelTitle;
    }

    public void setLabelTitle(String labelTitle) {
        this.labelTitle = labelTitle;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Label(String labelTitle, String labelName, Type type) {
        this.labelTitle = labelTitle;
        this.labelName = labelName;
        this.type = type;
    }

    public Label(String labelTitle, String labelName) {
        this.labelTitle = labelTitle;
        this.labelName = labelName;
        this.type = Type.STRING;
    }

    public Label() {
    }

    private String labelTitle;
    private String labelName;
    private Type type;
}
