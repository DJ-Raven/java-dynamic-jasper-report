package raven.dynamicjasper.expression;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;

/**
 *
 * @author Raven
 */
public class ParameterExpression extends AbstractSimpleExpression<Object> {

    private static final long serialVersionUID = 111;
    private final String name;

    public ParameterExpression(String name) {
        this.name = name;
    }

    @Override
    public Object evaluate(ReportParameters rp) {
        return rp.getParameterValue(name);
    }
}
