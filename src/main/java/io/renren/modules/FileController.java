package io.renren.modules;

import io.renren.common.utils.R;
import io.renren.modules.api.annotation.AuthIgnore;
import io.renren.modules.api.dao.UserDao;
import io.renren.modules.resource.model.ResourceAgreement;
import io.renren.modules.resource.model.ResourcePoolModel;
import io.renren.modules.resource.model.ResourceTradeMark;
import io.renren.modules.resource.service.ResourceAgreementService;
import io.renren.modules.resource.service.ResourcePoolService;
import io.renren.modules.resource.service.ResourceTradeMarkService;
import io.renren.modules.resource.vo.UserInfoVo;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserTokenService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
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
    @Value("${constant.imgBasePath}")
    private String imgBasePath;
    @Value("${constant.agreementImgBasePath}")
    private String agreementImgBasePath;

    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    @Resource
    private ResourceTradeMarkService resourceTradeMarkService;
    @Resource
    private ResourcePoolService resourcePoolService;
    @Resource
    private ResourceAgreementService resourceAgreementService;
    @Resource
    private SysUserTokenService sysUserTokenService;
    @AuthIgnore
    @RequestMapping(value = "/upload/{operation}")
    public R upload(@RequestParam String token,@RequestParam(required = false) String agreementOperatType,@RequestParam(required = false) String tradeMarkId,@RequestParam String agreementId,@PathVariable String operation,MultipartFile file) {
        try {
            if (StringUtils.isBlank(token)||StringUtils.isBlank(agreementId)||file.isEmpty()) {
                return R.error("文件为空");
            }
            UserInfoVo userInfoVo = sysUserTokenService.queryUserByToken(token);
            if(userInfoVo==null){
                return R.error("用户信息错误");
            }
            String username = userInfoVo.getUsername();
            Date date = new Date();
            // 获取文件名
            String OriginalFileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + OriginalFileName);
            // 获取文件的后缀名
            String suffixName = OriginalFileName.substring(OriginalFileName.lastIndexOf("."));
            String filePath = "";
            String destFileName="";
            //商标图片
            if("tradeMarkImg".equals(operation)){
                destFileName="tradeMark_img_"+tradeMarkId+suffixName;
                filePath="tradeMarkImg\\"+destFileName;
                ResourceTradeMark resourceTradeMark = new ResourceTradeMark();
                resourceTradeMark.setId(tradeMarkId);
                resourceTradeMark.setUpdateDate(date);
                resourceTradeMark.setImg(destFileName);
                resourceTradeMark.setAgreementId(agreementId);
                resourceTradeMark.setDeleteFlag("0");
                ResourceTradeMark resourceTradeMarkExist = resourceTradeMarkService.selectByPrimaryKey(tradeMarkId);
               if(resourceTradeMarkExist!=null){
                   resourceTradeMark.setUpdateBy(username);
                   resourceTradeMark.setUpdateDate(date);
                   resourceTradeMarkService.update(resourceTradeMark);
               }else{
                   resourceTradeMark.setCreateBy(username);
                   resourceTradeMark.setUpdateBy(username);
                   resourceTradeMark.setCreateDate(date);
                   resourceTradeMark.setUpdateDate(date);
                   resourceTradeMark.setAdviser(username);
                   resourceTradeMarkService.save(resourceTradeMark);
               }

            //合同
            }else if("agreementFile".equals(operation)){
                String baseAgreementImgPath="agreementImg"+"\\"+agreementId;
                //重新上传
                if("afresh".equals(agreementOperatType)){
                //删除目录下文件


                }
                filePath=baseAgreementImgPath+"\\agreement_"+agreementId+"_"+System.currentTimeMillis()+suffixName;

            //合同确认书
            }else if("agreementSureDocImg".equals(operation)){
                destFileName="agreement_sure_doc_"+agreementId+suffixName;
                filePath="agreementSureDocImg\\"+destFileName;
                ResourceAgreement resourceAgreement = new ResourceAgreement();
                resourceAgreement.setAgreementId(agreementId);
                resourceAgreement.setSureDocImgName(destFileName);
                resourceAgreement.setUpdateDate(date);
                resourceAgreement.setUpdateBy(username);
                resourceAgreementService.update(resourceAgreement);
            //商标局图片
            }else if("governmentImg".equals(operation)){
                destFileName="tradeMark_government_"+tradeMarkId+suffixName;
                filePath="governmentImg\\"+destFileName;
                ResourceTradeMark resourceTradeMark = new ResourceTradeMark();
                resourceTradeMark.setId(tradeMarkId);
                resourceTradeMark.setGovernmentImg(destFileName);
                resourceTradeMark.setUpdateDate(date);
                resourceTradeMark.setAgreementId(agreementId);
                resourceTradeMark.setDeleteFlag("0");
                ResourceTradeMark resourceTradeMarkExist = resourceTradeMarkService.selectByPrimaryKey(tradeMarkId);
                if(resourceTradeMarkExist!=null){
                    resourceTradeMark.setUpdateBy(username);
                    resourceTradeMark.setUpdateDate(date);
                    resourceTradeMarkService.update(resourceTradeMark);
                }else{
                    resourceTradeMark.setCreateBy(username);
                    resourceTradeMark.setUpdateBy(username);
                    resourceTradeMark.setCreateDate(date);
                    resourceTradeMark.setUpdateDate(date);
                    resourceTradeMark.setAdviser(username);
                    resourceTradeMarkService.save(resourceTradeMark);
                }

             //身份证正面
            }else if("icdImgFace".equals(operation)){
                destFileName="icd_face_"+agreementId+suffixName;
                filePath="icdImg\\"+destFileName;
                ResourceAgreement resourceAgreement = new ResourceAgreement();
                resourceAgreement.setAgreementId(agreementId);
                resourceAgreement.setIcdFaceImgName(destFileName);
                resourceAgreement.setUpdateDate(date);
                resourceAgreement.setUpdateBy(username);
                resourceAgreementService.update(resourceAgreement);
            //身份证反面
            } else if("icdImgReverse".equals(operation)){
                destFileName="icd_reverse_"+agreementId+suffixName;
                filePath="icdImg\\"+destFileName;
                ResourceAgreement resourceAgreement = new ResourceAgreement();
                resourceAgreement.setAgreementId(agreementId);
                resourceAgreement.setIcdReverseImgName(destFileName);
                resourceAgreement.setUpdateDate(date);
                resourceAgreement.setUpdateBy(username);
                resourceAgreementService.update(resourceAgreement);
            //执照
            }else if("licenseImg".equals(operation)){
                destFileName="license_"+agreementId+suffixName;
                filePath="licenseImg\\"+destFileName;
                ResourceAgreement resourceAgreement = new ResourceAgreement();
                resourceAgreement.setAgreementId(agreementId);
                resourceAgreement.setLicenseImgName(destFileName);
                resourceAgreement.setUpdateBy(username);
                resourceAgreement.setUpdateDate(date);
                resourceAgreementService.update(resourceAgreement);
            //委托书
            }else if("proxyImg".equals(operation)){
                destFileName="proxy_"+agreementId+suffixName;
                filePath="proxyImg\\"+destFileName;
                ResourceAgreement resourceAgreement = new ResourceAgreement();
                resourceAgreement.setAgreementId(agreementId);
                resourceAgreement.setProxyImgName(destFileName);
                resourceAgreement.setUpdateDate(date);
                resourceAgreement.setUpdateBy(username);
                resourceAgreementService.update(resourceAgreement);
            //商标确认书
            }else if("trademarkSureDoc".equals(operation)){
                destFileName="tradeMark_sureDoc_"+tradeMarkId+suffixName;
                filePath="trademarkSureDoc\\"+destFileName;
                ResourceTradeMark resourceTradeMark = new ResourceTradeMark();
                resourceTradeMark.setId(tradeMarkId);
                resourceTradeMark.setSureDocImg(destFileName);
                resourceTradeMark.setUpdateDate(date);
                resourceTradeMark.setAgreementId(agreementId);
                resourceTradeMark.setDeleteFlag("0");
                ResourceTradeMark resourceTradeMarkExist = resourceTradeMarkService.selectByPrimaryKey(tradeMarkId);
                if(resourceTradeMarkExist!=null){
                    resourceTradeMark.setUpdateBy(username);
                    resourceTradeMark.setUpdateDate(date);
                    resourceTradeMarkService.update(resourceTradeMark);
                }else{
                    resourceTradeMark.setCreateBy(username);
                    resourceTradeMark.setUpdateBy(username);
                    resourceTradeMark.setCreateDate(date);
                    resourceTradeMark.setUpdateDate(date);
                    resourceTradeMark.setAdviser(username);
                    resourceTradeMarkService.save(resourceTradeMark);
                }

            }


            log.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            String path = imgBasePath+filePath ;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                // 新建文件夹
                dest.getParentFile().mkdirs();
            }
            if(dest.exists()){
                dest.deleteOnExit();
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

    /**
     * IO流读取图片 by:long
     * @return
     */
    @RequestMapping(value = "/readImage", method = RequestMethod.GET)
    @AuthIgnore
    public void IoReadImage(@RequestParam String imgName, HttpServletResponse response) throws IOException {
        String extPath="";
        if(imgName.startsWith("tradeMark_img")){
            extPath="tradeMarkImg\\";
        }else if(imgName.startsWith("agreement_sure_doc")){
            extPath="agreementSureDocImg\\";
        }else if(imgName.startsWith("tradeMark_government")){
            extPath="governmentImg\\";
        }else if(imgName.startsWith("icd")){
            extPath="icdImg\\";
        }else if(imgName.startsWith("license")){
            extPath="licenseImg\\";
        }else if(imgName.startsWith("proxy")){
            extPath="proxyImg\\";
        }else if(imgName.startsWith("tradeMark_sureDoc")){
            extPath="trademarkSureDoc\\";
        }
        String imgPath=imgBasePath+extPath+imgName;
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
            //获取图片存放路径
            ips = new FileInputStream(new File(imgPath));
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
            ips.close();
        }
    }
    @RequestMapping("/agreement/delete/{agreementId}")
    public R deleteAgreementImg(@PathVariable String agreementId) {
        File dest = new File(agreementImgBasePath+agreementId);
        if(dest.isDirectory()){
            String[] list = dest.list();
            if(list==null||list.length<=0){
                return R.ok("文件不存在");
            }
            File temp = null;
            for(String fileName:list){
                temp = new File(agreementImgBasePath+agreementId+File.separator + fileName);
                temp.delete();
            }
        }
        return R.ok("删除成功");
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
