package dynamic_subjtable;

import javax.swing.table.TableModel;
import raven.dynamicjasper.DynamicJasperPrint;
import raven.dynamicjasper.builder.DataSource;
import raven.dynamicjasper.template.Column;
import raven.dynamicjasper.template.Label;
import raven.dynamicjasper.template.PageFormat;
import raven.dynamicjasper.template.Template_Report_Master_Detail;

/**
 *
 * @author Raven
 */
public class PrintTableDetails {

    public DynamicJasperPrint printTable(TableModel model, String title, ReportOption option) {
        return printTable(model, title, null, option);
    }

    public DynamicJasperPrint printTable(TableModel model, String title, PageFormat pageFormat, ReportOption option) {
        DynamicJasperPrint dynamicJasperPrint = new DynamicJasperPrint();
        Template_Report_Master_Detail tem = pageFormat == null ? new Template_Report_Master_Detail(title) : new Template_Report_Master_Detail(title, pageFormat);
        //  setup column report

        tem.addColumn(createColumn(option));
        //  setup header report
        tem.addLabelHeader(new Label());
        tem.addLabelHeader(new Label("Developer :", "dev", Label.Type.STRING));
        dynamicJasperPrint.setTemplate(tem);

        //  init data
        dynamicJasperPrint.setDataSource(createDataSource(model, true));
        dynamicJasperPrint.addParameter("dev", "Raven");
        return dynamicJasperPrint;
    }

    private DataSource createDataSource(TableModel model, boolean firstRow) {
        int column = model.getColumnCount();
        String[] columns = new String[column];
        for (int i = 0; i < column; i++) {
            columns[i] = "ex_" + i;
        }
        DataSource dataSource = new DataSource(columns);
        for (int i = 0; i < model.getRowCount(); i++) {
            if (i == 0 && firstRow == false) {
                continue;
            }
            Object[] row = new Object[column];
            for (int j = 0; j < column; j++) {
                Object data = model.getValueAt(i, j);
                if (data instanceof TableModel) {
                    row[j] = createDataSource((TableModel) data, false);
                } else {
                    row[j] = data;
                }
            }
            dataSource.add(row);
        }
        return dataSource;
    }

    private Column[] createColumn(ReportOption option) {
        Column columns[] = new Column[option.getOptions().length];
        int index = -1;
        for (Object obj : option.getOptions()) {
            if (obj instanceof ReportOption) {
                ReportOption sobj = (ReportOption) obj;
                columns[++index] = new Column(sobj.getTitle(), "ex_" + index, Column.Type.REPORT_DETAIL).setWidth(400);
                columns[index].addColumn(createColumn(sobj));
            } else {
                columns[++index] = new Column(obj + "", "ex_" + index, Column.Type.STRING);
            }
        }
        return columns;
    }
}
