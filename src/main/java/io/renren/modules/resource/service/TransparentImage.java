package io.renren.modules.resource.service;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.UnitValue;

import java.io.IOException;

/**
 * @author jinweia.wu
 */
public class TransparentImage implements IEventHandler {
    protected PdfExtGState gState;
    protected Image logoImg;
    protected Image markImg;
    public TransparentImage(Image logoImg,Image markImg) {
        this.logoImg = logoImg;
        this.markImg = markImg;
        gState = new PdfExtGState().setFillOpacity(0.2f);
    }
    @Override
    public void handleEvent(Event event){

        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdf = docEvent.getDocument();
        int numberOfPages = pdf.getNumberOfPages();
        PdfPage page = docEvent.getPage();
        int pageNumber = pdf.getPageNumber(page);
        Rectangle pageSize = page.getPageSize();
        PdfCanvas pdfCanvas = new PdfCanvas(
                page.newContentStreamBefore(), page.getResources(), pdf);
        pdfCanvas.saveState();
        Canvas canvas = new Canvas(pdfCanvas, pdf, page.getPageSize());
        // canvas.add(img.scaleAbsolute(pageSize.getWidth(), pageSize.getHeight()));
        canvas.add(logoImg.setWidth(UnitValue.createPercentValue(47)));
        canvas.add(markImg.setFixedPosition(140f,200f));
        PdfFont f2 = null;
        try {
            f2 = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfCanvas.beginText()
                .setFontAndSize(f2, 10)
                .moveText(pageSize.getWidth() / 2 - 120, pageSize.getTop() - 20)
                .moveText(120, -pageSize.getTop() + 40)
                .showText(String.valueOf(pageNumber))
                .endText();
        pdfCanvas.restoreState();
        pdfCanvas.release();
    }
}