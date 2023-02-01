package raven.dynamicjasper.template;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.LineStyle;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import raven.dynamicjasper.builder.BuildColumn;
import raven.dynamicjasper.expression.ParameterExpression;
import raven.dynamicjasper.style.Style;

/**
 *
 * @author Raven
 */
public class Template_Report_Master_Detail implements Template {

    private Style styleTitle;
    private Style stylePageHeader;
    private Style styleColumnTitle;
    private Style styleColumn;
    private Style styleDetail;
    private Style stylePageFooter;
    private Style styleSummary;
    private String title;
    private String date;
    private List<Column> columns;
    private List<Label> labelsHeader;
    private List<Label> labelsSummary;
    private final String PAGE_X_Y_FORMAT = "Page {0} / {1}";
    private PageFormat pageFormat;

    public Template_Report_Master_Detail() {
        this("", new PageFormat(PageType.A4, 0, 0, PageOrientation.PORTRAIT));
    }

    public Template_Report_Master_Detail(String title, PageFormat pageFormat) {
        this.pageFormat = pageFormat;
        initStyle();
        init();
        this.title = title;
    }

    public Template_Report_Master_Detail(String title) {
        this();
        this.title = title;
    }

    public Template_Report_Master_Detail setTitle(String title) {
        this.title = title;
        return this;
    }

    private void init() {
        columns = new ArrayList<>();
        labelsHeader = new ArrayList<>();
        labelsSummary = new ArrayList<>();
        title = "Template Report Master Detail";
        date = new SimpleDateFormat("dd/MM/yyyy - hh:mm aa").format(new Date());
    }

    private JasperReportBuilder initReport() {
        JasperReportBuilder report = DynamicReports.report();
        report.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL);
        if (pageFormat.getPageType() != null) {
            report.setPageFormat(pageFormat.getPageType(), pageFormat.getPageOrientation());
        } else {
            report.setPageFormat(pageFormat.getPageWidth(), pageFormat.getPageHeight(), pageFormat.getPageOrientation());
        }
        report.setPageMargin(DynamicReports.margin(20));
        report.setPageHeaderStyle(stylePageHeader);
        report.setColumnTitleStyle(styleColumnTitle);
        report.setColumnStyle(styleColumn);
        report.setDetailStyle(styleDetail);
        report.setPageFooterStyle(stylePageFooter);
        //  init components
        report.pageFooter(DynamicReports.cmp.horizontalFlowList(DynamicReports.cmp.text(date).setStyle(stylePageFooter).setHorizontalAlignment(HorizontalAlignment.LEFT), DynamicReports.cmp.pageXofY().setFormatExpression(PAGE_X_Y_FORMAT).setStyle(stylePageFooter)));
        report.title(DynamicReports.cmp.text(title).setStyle(styleTitle));
        report.pageHeader(DynamicReports.cmp.line().setPen(DynamicReports.stl.pen(0.5f, LineStyle.SOLID)));
        initLabelHeader(report);
        initLabelSummary(report);
        new BuildColumn().initColumns(report, columns, styleColumn, styleColumnTitle);
        return report;
    }

    private void initLabelHeader(JasperReportBuilder report) {
        VerticalListBuilder v_header = DynamicReports.cmp.verticalList();
        for (int i = 0; i < labelsHeader.size(); i += 2) {
            HorizontalListBuilder h_header = DynamicReports.cmp.horizontalList();
            h_header.setGap(25);
            for (int j = 0; j < 2; j++) {
                if (i + j < labelsHeader.size()) {
                    Label label = labelsHeader.get(i + j);
                    if (label.getType() == Label.Type.NUMBER) {
                        h_header.add(DynamicReports.cmp.horizontalList(DynamicReports.cmp.text(label.getLabelTitle()).setStyle(stylePageHeader), DynamicReports.cmp.text(new ParameterExpression(label.getLabelName())).setPattern("#,##0.##").setStyle(stylePageHeader).setHorizontalAlignment(HorizontalAlignment.RIGHT)));
                    } else {
                        h_header.add(DynamicReports.cmp.horizontalList(DynamicReports.cmp.text(label.getLabelTitle()).setStyle(stylePageHeader), DynamicReports.cmp.text(new ParameterExpression(label.getLabelName())).setStyle(stylePageHeader).setHorizontalAlignment(HorizontalAlignment.RIGHT)));
                    }
                } else {
                    h_header.add(DynamicReports.cmp.horizontalList(DynamicReports.cmp.text(""), DynamicReports.cmp.text("")));
                }
            }
            v_header.add(h_header);
        }
        report.pageHeader(v_header);
    }

    private void initLabelSummary(JasperReportBuilder report) {
        VerticalListBuilder v_header = DynamicReports.cmp.verticalList();
        for (int i = 0; i < labelsSummary.size(); i += 2) {
            HorizontalListBuilder h_header = DynamicReports.cmp.horizontalList();
            h_header.setGap(25);
            for (int j = 0; j < 2; j++) {
                if (i + j < labelsSummary.size()) {
                    Label label = labelsSummary.get(i + j);
                    if (label.getType() == Label.Type.NUMBER) {
                        h_header.add(DynamicReports.cmp.horizontalList(DynamicReports.cmp.text(label.getLabelTitle()).setStyle(styleSummary), DynamicReports.cmp.text(new ParameterExpression(label.getLabelName())).setPattern("#,##0.##").setStyle(styleSummary).setHorizontalAlignment(HorizontalAlignment.RIGHT)));
                    } else {
                        h_header.add(DynamicReports.cmp.horizontalList(DynamicReports.cmp.text(label.getLabelTitle()).setStyle(styleSummary), DynamicReports.cmp.text(new ParameterExpression(label.getLabelName())).setStyle(styleSummary).setHorizontalAlignment(HorizontalAlignment.RIGHT)));
                    }
                } else {
                    h_header.add(DynamicReports.cmp.horizontalList(DynamicReports.cmp.text(""), DynamicReports.cmp.text("")));
                }
            }
            v_header.add(h_header);
        }
        report.summary(v_header);
    }

    private void initStyle() {
        styleTitle = new Style();
        styleTitle.setFont(DynamicReports.stl.font().bold().setFontSize(15)).setHorizontalAlignment(HorizontalAlignment.CENTER).setPadding(5);
        stylePageHeader = new Style();
        stylePageHeader.setFont(DynamicReports.stl.font().bold().setFontSize(10)).setPadding(2);
        styleColumnTitle = new Style();
        styleColumnTitle.setBorder(DynamicReports.stl.pen(0.5f, LineStyle.SOLID)).setFont(DynamicReports.stl.font().bold().setFontSize(10)).setPadding(2).setBackgroundColor(new Color(204, 206, 240));
        styleColumn = new Style();
        styleColumn.setBorder(DynamicReports.stl.pen(0.5f, LineStyle.SOLID)).setFont(DynamicReports.stl.font().setFontSize(10)).setPadding(2);
        styleDetail = new Style();
        styleDetail.setBorder(DynamicReports.stl.pen(0.5f, LineStyle.SOLID)).setFont(DynamicReports.stl.font().setFontSize(10));
        stylePageFooter = new Style();
        stylePageFooter.setFont(DynamicReports.stl.font().setFontSize(10)).setHorizontalAlignment(HorizontalAlignment.RIGHT).setPadding(2);
        styleSummary = new Style();
        styleSummary.setFont(DynamicReports.stl.font().bold().setFontSize(10)).setPadding(2);
    }

    public StyleBuilder newStyle() {
        return DynamicReports.stl.style();
    }

    public StyleBuilder newStyle(Style style) {
        return DynamicReports.stl.style(style);
    }

    @Override
    public JasperReportBuilder getReportTemplate() {
        return initReport();
    }

    public Template_Report_Master_Detail addColumn(Column... columns) {
        for (Column c : columns) {
            this.columns.add(c);
        }
        return this;
    }

    public Template_Report_Master_Detail addLabelHeader(Label... labels) {
        for (Label label : labels) {
            this.labelsHeader.add(label);
        }
        return this;
    }

    public Template_Report_Master_Detail addLabelSummary(Label... labels) {
        for (Label label : labels) {
            this.labelsSummary.add(label);
        }
        return this;
    }

    public Template_Report_Master_Detail clearColumns() {
        columns.clear();
        return this;
    }

    public void setStyleTitle(Style styleTitle) {
        this.styleTitle = styleTitle;
    }

    public void setStylePageHeader(Style stylePageHeader) {
        this.stylePageHeader = stylePageHeader;
    }

    public void setStyleColumnTitle(Style styleColumnTitle) {
        this.styleColumnTitle = styleColumnTitle;
    }

    public void setStyleColumn(Style styleColumn) {
        this.styleColumn = styleColumn;
    }

    public void setStyleDetail(Style styleDetail) {
        this.styleDetail = styleDetail;
    }

    public void setStylePageFooter(Style stylePageFooter) {
        this.stylePageFooter = stylePageFooter;
    }

    public void setStyleSummary(Style styleSummary) {
        this.styleSummary = styleSummary;
    }

    public Style getStyleTitle() {
        return styleTitle;
    }

    public Style getStylePageHeader() {
        return stylePageHeader;
    }

    public Style getStyleColumnTitle() {
        return styleColumnTitle;
    }

    public Style getStyleColumn() {
        return styleColumn;
    }

    public StyleBuilder getStyleDetail() {
        return styleDetail;
    }

    public StyleBuilder getStylePageFooter() {
        return stylePageFooter;
    }

    public StyleBuilder getStyleSummary() {
        return styleSummary;
    }

    public void setPageFormat(PageType pageType) {
        pageFormat = new PageFormat(pageType, 0, 0, PageOrientation.PORTRAIT);
    }

    public void setPageFormat(PageType pageType, PageOrientation pageorientation) {
        pageFormat = new PageFormat(pageType, 0, 0, pageorientation);
    }

    public void setPageFormat(int width, int height) {
        pageFormat = new PageFormat(null, width, height, PageOrientation.PORTRAIT);
    }

    public void setPageFormat(int width, int height, PageOrientation pageorientation) {
        pageFormat = new PageFormat(null, width, height, pageorientation);
    }
}
