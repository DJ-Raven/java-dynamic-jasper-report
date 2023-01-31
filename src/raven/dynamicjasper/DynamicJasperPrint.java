package raven.dynamicjasper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperImageExporterBuilder;
import net.sf.dynamicreports.jasper.constant.ImageType;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import raven.dynamicjasper.template.Template;

/**
 *
 * @author Raven
 */
public class DynamicJasperPrint {

    private JasperReportBuilder report;
    private Template template;

    public DynamicJasperPrint() {
    }

    public DynamicJasperPrint setTemplate(Template template) {
        this.template = template;
        report = template.getReportTemplate();
        return this;
    }

    public void view() throws DRException {
        report.show(false);
    }

    public void print(boolean printOption) throws DRException {
        report.print(printOption);
    }

    public void printTo(String printerName) throws JRException, DRException {
        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        printRequestAttributeSet.add(new Copies(1));
        PrinterName printer = new PrinterName(printerName, null);
        PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
        printServiceAttributeSet.add(printer);
        JRPrintServiceExporter exporter = new JRPrintServiceExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, report.toJasperPrint());
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);
        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
        exporter.exportReport();
    }

    public void addParameter(String name, Object value) {
        report.addParameter(name, value);
    }

    public void setDataSource(DRDataSource dr) {
        report.setDataSource(dr);
    }

    public File exportToPdf(File path) throws IOException, DRException {
        File file = new File(path.getAbsolutePath() + ".pdf");
        OutputStream out = new FileOutputStream(file);
        report.toPdf(out);
        out.close();
        return file;
    }

    public File exportToWord(File path) throws IOException, DRException {
        File file = new File(path.getAbsolutePath() + ".rtf");
        OutputStream out = new FileOutputStream(file);
        report.toRtf(out);
        out.close();
        return file;
    }

    public File exportToExcel(File path) throws IOException, DRException {
        File file = new File(path.getAbsolutePath() + ".xls");
        OutputStream out = new FileOutputStream(file);
        report.toXls(out);
        out.close();
        return file;
    }

    public File exportToImage(File path, int startPageIndex, int endPageIndex) throws IOException, DRException {
        File file = new File(path.getAbsolutePath() + ".jpg");
        JasperImageExporterBuilder ex = DynamicReports.export.imageExporter(file, ImageType.JPG);
        ex.setStartPageIndex(startPageIndex);
        ex.setEndPageIndex(endPageIndex);
        report.toImage(ex);
        return file;
    }

    public File exportToImage(File path) throws IOException, DRException {
        File file = new File(path.getAbsolutePath() + ".jpg");
        JasperImageExporterBuilder ex = DynamicReports.export.imageExporter(file, ImageType.JPG);
        report.toImage(ex);
        return file;
    }

    public Template getTemplate() {
        return template;
    }
}
