package raven.dynamicjasper.expression;

import java.util.List;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;
import raven.dynamicjasper.builder.BuildColumn;
import raven.dynamicjasper.template.Column;

/**
 *
 * @author Raven
 */
public class SubreportExpression extends AbstractSimpleExpression<JasperReportBuilder> {

    private static final long serialVersionUID = 333L;
    private final List<Column> columns;
    private final StyleBuilder styleColumn;
    private final StyleBuilder styleColumnTitle;

    public SubreportExpression(List<Column> columns, StyleBuilder styleColumn, StyleBuilder styleColumnTitle) {
        this.columns = columns;
        this.styleColumn = styleColumn;
        this.styleColumnTitle = styleColumnTitle;
    }

    @Override
    public JasperReportBuilder evaluate(ReportParameters rp) {
        JasperReportBuilder report = DynamicReports.report();
        report.setColumnTitleStyle(styleColumnTitle);
        new BuildColumn().initColumns(report, columns, styleColumn, styleColumnTitle);
        return report;
    }
}
