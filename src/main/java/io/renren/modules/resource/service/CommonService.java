package io.renren.modules.resource.service;


import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.AreaBreakType;
import io.renren.modules.resource.model.ResourceTradeMark;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.util.List;

@Service
public class CommonService {

    @Value("${nonChapterFileDir}")
    private String nonChapterFileDir;
    @Value("${hasChapterFileDir}")
    private String hasChapterFileDir;

    private static String templateFilePath = "src/main/resources/agreement_resource/zy_agreement_tradeMark_1.pdf";
    /*添加商标*/
    private static String  logImageUrl = "src/main/resources/agreement_resource/logo.png";
    private static String  markImageUrl = "src/main/resources/agreement_resource/mark.png";
    public Map<String,String> manipulatePdf(String agreementId,Map<String, String> fieldValueMap, List<ResourceTradeMark> tradeMarkets) throws Exception {

        String nonChapterFileName = "zy_non_chapter_"+agreementId;
        String hasChapterFileName = "zy_has_chapter_"+agreementId;
        String nonChapterFilePath = nonChapterFileDir + nonChapterFileName + ".pdf";
        String hasChapterFilePath = hasChapterFileDir + hasChapterFileName + ".pdf";
        //判断文件是否存在
        File nonChapterFileFile = new File(nonChapterFilePath);
        File hasChapterFileFile = new File(hasChapterFilePath);
        // 检测是否存在目录
        if(nonChapterFileFile.exists()){
            nonChapterFileFile.deleteOnExit();
        }
        if(hasChapterFileFile.exists()){
            hasChapterFileFile.deleteOnExit();
        }
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(templateFilePath), new PdfWriter(nonChapterFilePath));
        Image logImg = new Image(ImageDataFactory.create(logImageUrl));
        Image markImg = new Image(ImageDataFactory.create(markImageUrl));
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new TransparentImage(logImg, markImg));
        PdfAcroForm pdfAcroForm = PdfAcroForm.getAcroForm(pdfDoc, true);
        Map<String, PdfFormField> formFields = pdfAcroForm.getFormFields();
        Set<String> strings = formFields.keySet();
        Iterator<String> iterator = strings.iterator();
        PdfFont f2 = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", true);
        while (iterator.hasNext()) {
            String fieldName = iterator.next();
            if (("pay_type".equals(fieldName))) {
                PdfFormField radioBox = formFields.get(fieldName);
                String payTypeValue = fieldValueMap.get(fieldName);
                radioBox.setValue(payTypeValue);
                if("only_one_pay".equals(payTypeValue)){
                    PdfFormField name = formFields.get("allCost_1");
                    String fieldValue = fieldValueMap.get("allCost_1");
                    name.setFont(f2);
                    name.setFontSize(12);
                    name.setValue(fieldValue);
                }else if("stages_pay".equals(payTypeValue)){
                    PdfFormField earnestField = formFields.get("earnest");
                    String earnestFieldValue = fieldValueMap.get("earnest");
                    earnestField.setFont(f2);
                    earnestField.setFontSize(12);
                    earnestField.setValue(earnestFieldValue);

                    PdfFormField finalPaymentField = formFields.get("final_payment");
                    String finalPaymentFieldValue = fieldValueMap.get("final_payment");
                    finalPaymentField.setFont(f2);
                    finalPaymentField.setFontSize(12);
                    finalPaymentField.setValue(finalPaymentFieldValue);
                }
            }
            if (!(("allCost_1".equals(fieldName))||
                    ("earnest".equals(fieldName)) ||
                    ("final_payment".equals(fieldName))||
                    ("pay_type".equals(fieldName))||
                    ("pay_type.1".equals(fieldName))||
                    ("pay_type.2".equals(fieldName)))) {
                String fieldValue = fieldValueMap.get(fieldName);
                PdfFormField name = formFields.get(fieldName);
                if(StringUtils.isNotBlank(fieldValue)&&name!=null){
                    name.setFont(f2);
                    name.setFontSize(12);
                    name.setValue(fieldValue);
                }

            }
        }
        pdfAcroForm.flattenFields();

        Document document = new Document(pdfDoc);
        document.setTopMargin(80);
        document.setLeftMargin(10);
        //添加商标信息
        Table table = addDocTable(tradeMarkets,f2);
        //为了解决添加新的页面无法添加到新的一页bug
        PdfPage pdfPage = pdfDoc.addNewPage(4);

        document.add(new AreaBreak(AreaBreakType.LAST_PAGE));
        document.add(table);
        document.close();
        pdfDoc.close();
        //添加骑缝章和签约章
        addAgreementchapter(nonChapterFilePath,hasChapterFilePath);
        HashMap<String, String> fileNameMap = new HashMap<>();
        fileNameMap.put("hasChapterFileName",hasChapterFileName);
        fileNameMap.put("nonChapterFileName",nonChapterFileName);
        return fileNameMap;
    }

    private Table addDocTable(List<ResourceTradeMark> tradeMarkets, PdfFont f2) {
        ArrayList<String> tableHeaders = new ArrayList<>();
        tableHeaders.add("序号");
        tableHeaders.add("商标名称");
        tableHeaders.add("类型");
        tableHeaders.add("类别");
        tableHeaders.add("群组");
        tableHeaders.add("价格");
        Table table = new Table(new float[]{10, 20, 10, 15, 35, 10})
                .setWidthPercent(100);
        table.setMarginLeft(30);
        table.setMarginRight(30);
        table.setFixedLayout();
        table.setFont(f2);
        table.setKeepWithNext(true);
        Cell cell;
        //表头
        for (int i = 0; i < tableHeaders.size(); i++) {
            cell = new Cell().add(new Paragraph(tableHeaders.get(i)));
            table.addCell(cell);
        }
        for (int i = 0; i < tradeMarkets.size(); i++) {
            ResourceTradeMark resourceTradeMark = tradeMarkets.get(i);
            cell = new Cell().add(new Paragraph(i+1+""));
            table.addCell(cell);

            cell = new Cell().add(new Paragraph(resourceTradeMark.getName()));
            table.addCell(cell);

            cell = new Cell().add(new Paragraph(resourceTradeMark.getDocDes()));
            table.addCell(cell);

            cell = new Cell().add(new Paragraph(resourceTradeMark.getClassifyCode()));
            table.addCell(cell);

            cell = new Cell().add(new Paragraph(resourceTradeMark.getProtectProduct()));
            table.addCell(cell);

            cell = new Cell().add(new Paragraph(""));
            table.addCell(cell);
        }
        return table;
    }

    private void addAgreementchapter(String nonChapterFilePath,String hasChapterFilePath) throws Exception {
        PdfDocument pdfDocFinal = new PdfDocument(new PdfReader(nonChapterFilePath), new PdfWriter(hasChapterFilePath));
        int numberOfPages = pdfDocFinal.getNumberOfPages();
        for (int i = 1; i <= numberOfPages; i++) {
            PdfPage page = pdfDocFinal.getPage(i);

            String chapterImgUrl = "src/main/resources/agreement_resource/chapter.png";
            //添加签约章
            if (i == 2 || i == 3) {
                PdfCanvas chapterPdfCanvas = new PdfCanvas(
                        page.newContentStreamAfter(), page.getResources(), pdfDocFinal);
                chapterPdfCanvas.saveState();
                Canvas chapterCanvas = new Canvas(chapterPdfCanvas, pdfDocFinal, page.getPageSize());
                Image img = new Image(ImageDataFactory.create(chapterImgUrl));
                float imageWidth = img.getImageWidth();
                chapterCanvas.add(img.setFixedPosition((480 - imageWidth), 190));
                chapterPdfCanvas.restoreState();
                chapterPdfCanvas.release();
            }
            //添加骑缝章

            PdfCanvas pdfCanvas = new PdfCanvas(
                    page.newContentStreamAfter(), page.getResources(), pdfDocFinal);
            pdfCanvas.saveState();
            Canvas canvas = new Canvas(pdfCanvas, pdfDocFinal, page.getPageSize());
            String pagingSealImgUrl = "";
            if (numberOfPages == 4) {
                pagingSealImgUrl = "src/main/resources/agreement_resource/division_4/4_" + i + ".gif";
            } else if (numberOfPages == 5) {
                pagingSealImgUrl = "src/main/resources/agreement_resource/division_5/5_" + i + ".gif";
            } else if (numberOfPages == 6) {
                pagingSealImgUrl = "src/main/resources/agreement_resource/division_6/6_" + i + ".gif";
            }
            Image img = new Image(ImageDataFactory.create(pagingSealImgUrl));
            float imageWidth = img.getImageWidth();
            canvas.add(img.setFixedPosition((float) (595.3 - imageWidth), 230));

            pdfCanvas.restoreState();
            pdfCanvas.release();
        }
        pdfDocFinal.close();
    }
}
