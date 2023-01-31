package raven.dynamicjasper.template;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raven
 */
public class Column {

    public static enum Type {
        STRING, NUMBER, IMAGE, REPORT_DETAIL
    }

    public static enum HorizontalAlignment {
        CENTER, LEFT, RIGHT
    }

    public static enum VerticalAlignment {
        TOP, MIDDLE, BOTTOM
    }

    public String getColumnTitle() {
        return columnTitle;
    }

    public Column setColumnTitle(String columnTitle) {
        this.columnTitle = columnTitle;
        return this;
    }

    public String getColumnName() {
        return columnName;
    }

    public Column setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Column setType(Type type) {
        this.type = type;
        return this;
    }

    public HorizontalAlignment getHorizontal() {
        return horizontal;
    }

    public Column setHorizontal(HorizontalAlignment horizontal) {
        this.horizontal = horizontal;
        return this;
    }

    public VerticalAlignment getVertical() {
        return vertical;
    }

    public Column setVertical(VerticalAlignment vertical) {
        this.vertical = vertical;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public Column setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public Column setHeight(int height) {
        this.height = height;
        return this;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public Column addColumn(Column... column) {
        if (columns == null) {
            columns = new ArrayList<>();
        }
        for (Column c : column) {
            this.columns.add(c);
        }
        return this;
    }

    public Column(String columnTitle, String columnName, Type type, HorizontalAlignment horizontal, VerticalAlignment vertical) {
        this.columnTitle = columnTitle;
        this.columnName = columnName;
        this.type = type;
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Column(String columnTitle, String columnName, Type type) {
        this.columnTitle = columnTitle;
        this.columnName = columnName;
        this.type = type;
        if (type == Type.NUMBER) {
            this.horizontal = HorizontalAlignment.RIGHT;
        } else {
            this.horizontal = HorizontalAlignment.LEFT;
        }
        this.vertical = VerticalAlignment.TOP;
    }

    public Column(String columnTitle, String columnName, Type type, HorizontalAlignment horizontal) {
        this.columnTitle = columnTitle;
        this.columnName = columnName;
        this.type = type;
        this.horizontal = horizontal;
        this.vertical = VerticalAlignment.TOP;
    }

    public Column(String columnTitle, String columnName, Type type, VerticalAlignment vertical) {
        this.columnTitle = columnTitle;
        this.columnName = columnName;
        this.type = type;
        this.vertical = vertical;
        this.horizontal = HorizontalAlignment.LEFT;
    }

    public Column(String columnName, Type type, HorizontalAlignment horizontal, VerticalAlignment vertical) {
        this.columnName = columnName;
        this.type = type;
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Column(String columnName, Type type) {
        this.columnName = columnName;
        this.type = type;
        if (type == Type.NUMBER) {
            this.horizontal = HorizontalAlignment.RIGHT;
        } else {
            this.horizontal = HorizontalAlignment.LEFT;
        }
        this.vertical = VerticalAlignment.TOP;
    }

    public Column(String columnName, Type type, HorizontalAlignment horizontal) {
        this.columnName = columnName;
        this.type = type;
        this.horizontal = horizontal;
        this.vertical = VerticalAlignment.TOP;
    }

    public Column(String columnName, Type type, VerticalAlignment vertical) {
        this.columnName = columnName;
        this.type = type;
        this.vertical = vertical;
        this.horizontal = HorizontalAlignment.LEFT;
    }

    public Column() {
    }

    private List<Column> columns;
    private String columnTitle;
    private String columnName;
    private Type type;
    private HorizontalAlignment horizontal;
    private VerticalAlignment vertical;
    private int width = -1;
    private int height = -1;
}
