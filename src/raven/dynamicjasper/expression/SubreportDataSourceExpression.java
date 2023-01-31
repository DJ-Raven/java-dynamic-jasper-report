package raven.dynamicjasper.expression;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.JRDataSource;

/**
 *
 * @author Raven
 */
public class SubreportDataSourceExpression extends AbstractSimpleExpression<JRDataSource> {

    private static final long serialVersionUID = 222L;
    private final String name;

    public SubreportDataSourceExpression(String name) {
        this.name = name;
    }

    @Override
    public JRDataSource evaluate(ReportParameters rp) {
        DRDataSource dataSource = rp.getFieldValue(name);
        return dataSource;
    }
}
