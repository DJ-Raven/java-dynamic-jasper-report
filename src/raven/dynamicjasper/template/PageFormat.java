package raven.dynamicjasper.template;

import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;

/**
 *
 * @author Raven
 */
public class PageFormat {

    public PageType getPageType() {
        return pageType;
    }

    public void setPageType(PageType pageType) {
        this.pageType = pageType;
    }

    public int getPageWidth() {
        return pageWidth;
    }

    public void setPageWidth(int pageWidth) {
        this.pageWidth = pageWidth;
    }

    public int getPageHeight() {
        return pageHeight;
    }

    public void setPageHeight(int pageHeight) {
        this.pageHeight = pageHeight;
    }

    public PageOrientation getPageOrientation() {
        return pageOrientation;
    }

    public void setPageOrientation(PageOrientation pageOrientation) {
        this.pageOrientation = pageOrientation;
    }

    private PageType pageType = null;
    private int pageWidth;
    private int pageHeight;
    private PageOrientation pageOrientation;

    public PageFormat() {
    }

    public PageFormat(PageType pageType, int pageWidth, int pageHeight, PageOrientation pageOrientation) {
        this.pageType = pageType;
        this.pageWidth = pageWidth;
        this.pageHeight = pageHeight;
        this.pageOrientation = pageOrientation;
    }
}
