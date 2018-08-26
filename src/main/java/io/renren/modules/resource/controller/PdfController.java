package io.renren.modules.resource.controller;

import io.renren.common.utils.R;
import io.renren.modules.api.annotation.AuthIgnore;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * pdf相关接口
 */
@RequestMapping("/api/pdf")
@RestController
public class PdfController {
    @Value("${web.upload-path}")
    private String pdfDir;

    @AuthIgnore
    @RequestMapping("/show/{pdfTypt}/{pdfId}")
    public R save(@PathVariable String pdfTypt,@PathVariable String pdfId,HttpServletResponse response){
       String pdfPath=pdfDir;
        try {
            if("non".equals(pdfTypt)){
                pdfPath=pdfPath+"pdfData/non_chapter/";
            }else if("has".equals(pdfTypt)){
                pdfPath=pdfPath+"pdfData/has_chapter/";
            }
            File file = new File(pdfPath+pdfId+".pdf");
            FileInputStream fileInputStream = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment;fileName=test.pdf");
            response.setContentType("multipart/form-data");
            OutputStream outputStream = response.getOutputStream();
            IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return R.ok();
    }

}
