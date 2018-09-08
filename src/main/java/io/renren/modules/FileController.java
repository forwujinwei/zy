package io.renren.modules;

import io.renren.common.utils.R;
import io.renren.modules.api.annotation.AuthIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * 文件上传下载
 *
 * @author jinweia.wu
 * @create 2018-08-19 2:59
 **/
@RequestMapping("/api/file")
@RestController
public class FileController {
    private static final String fileDir="D:\\app_data\\imgData\\";
    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    @AuthIgnore
    @RequestMapping(value = "/upload/{agreementId}/{index}")
    public R upload(@PathVariable String agreementId,@PathVariable String index,MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return R.error("文件为空");
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            String filePath = fileDir;
            String path = filePath + agreementId+"_"+index+suffixName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                // 新建文件夹
                dest.getParentFile().mkdirs();
            }
            // 文件写入
            file.transferTo(dest);
            return R.ok("上传成功");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.error("上传失败");
    }

    @PostMapping("/batch")
    public R handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream  stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            String filePath = "/Users/dalaoyang/Downloads/";
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + file.getOriginalFilename())));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return R.error("第 " + i + " 个文件上传失败 ==> ");
                }
            } else {
                return R.error("第 " + i+ " 个文件上传失败因为文件为空");
            }
        }
        return R.ok("上传成功");
    }

    @GetMapping("/download")
    public R downloadFile(HttpServletRequest request, HttpServletResponse response) {
        // 文件名
        String fileName = "dalaoyang.jpeg";
        if (fileName != null) {
            //设置文件路径
            File file = new File("/Users/dalaoyang/Documents/dalaoyang.jpeg");
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return R.ok("下载成功");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return R.error("下载失败");
    }
}
