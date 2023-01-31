package raven.dynamicjasper.builder;

import java.util.List;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.builder.column.ComponentColumnBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import raven.dynamicjasper.expression.SubreportDataSourceExpression;
import raven.dynamicjasper.expression.SubreportExpression;
import raven.dynamicjasper.template.Column;

/**
 *
 * @author Raven
 */
public class BuildColumn {

    public void initColumns(JasperReportBuilder report, List<Column> columns, StyleBuilder styleColumn, StyleBuilder styleColumnTitle) {
        for (Column c : columns) {
            if (c.getType() == Column.Type.REPORT_DETAIL) {
                report.addColumn(getSubReport(c, styleColumn, styleColumnTitle));
                FieldBuilder field = DynamicReports.field(c.getColumnName(), DRDataSource.class);
                report.addField(field);
            } else {
                report.addColumn(getColumn(c, styleColumn, styleColumnTitle));
            }
        }
    }

    private ColumnBuilder getColumn(Column column, StyleBuilder styleColumn, StyleBuilder styleColumnTitle) {
        StyleBuilder col_style = DynamicReports.stl.style(styleColumn);
        StyleBuilder title_style = DynamicReports.stl.style(styleColumnTitle);
        HorizontalAlignment h;
        VerticalAlignment v;
        if (column.getHorizontal() == Column.HorizontalAlignment.CENTER) {
            h = HorizontalAlignment.CENTER;
        } else if (column.getHorizontal() == Column.HorizontalAlignment.LEFT) {
            h = HorizontalAlignment.LEFT;
        } else {
            h = HorizontalAlignment.RIGHT;
        }
        if (column.getVertical() == Column.VerticalAlignment.TOP) {
            v = VerticalAlignment.TOP;
        } else if (column.getVertical() == Column.VerticalAlignment.MIDDLE) {
            v = VerticalAlignment.MIDDLE;
        } else {
            v = VerticalAlignment.BOTTOM;
        }
        col_style.setHorizontalAlignment(h);
        col_style.setVerticalAlignment(v);
        title_style.setHorizontalAlignment(h);
        title_style.setVerticalAlignment(v);
        TextColumnBuilder c;
        if (column.getType() == Column.Type.NUMBER) {
            c = DynamicReports.col.column(column.getColumnName(), Object.class).setPattern("#,##0").setStyle(col_style).setTitleStyle(title_style);
        } else {
            c = DynamicReports.col.column(column.getColumnName(), Object.class).setStyle(col_style).setTitleStyle(title_style);
        }

        if (column.getWidth() >= 0) {
            c.setWidth(column.getWidth());
        }
        if (column.getHeight() >= 0) {
            c.setHeight(column.getHeight());
        }
        if (column.getColumnTitle() != null) {
            c.setTitle(column.getColumnTitle());
        }
        return c;
    }

    private ColumnBuilder getSubReport(Column column, StyleBuilder styleColumn, StyleBuilder styleColumnTitle) {
        List<Column> columns = column.getColumns();
        SubreportExpression srex = new SubreportExpression(columns, styleColumn, styleColumnTitle);
        SubreportBuilder subreport = DynamicReports.cmp.subreport(srex).setDataSource(new SubreportDataSourceExpression(column.getColumnName()));
        ComponentColumnBuilder detail = DynamicReports.col.componentColumn(subreport);
        if (column.getColumnTitle() != null) {
            detail.setTitle(column.getColumnTitle());
        }
        if (column.getWidth() >= 0) {
            detail.setWidth(column.getWidth());
        }
        if (column.getHeight() >= 0) {
            detail.setHeight(column.getWidth());
        }
        return detail;
    }
}
