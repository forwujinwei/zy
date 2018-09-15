package io.renren.modules.resource.controller;

import io.renren.common.constants.DistEnum;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.api.annotation.LoginUser;
import io.renren.modules.api.entity.UserEntity;
import io.renren.modules.resource.model.ResourceAgreement;
import io.renren.modules.resource.model.ResourcePersonalPoolModel;
import io.renren.modules.resource.model.ResourcePoolModel;
import io.renren.modules.resource.model.ResourceTradeMark;
import io.renren.modules.resource.service.CommonService;
import io.renren.modules.resource.service.ResourceAgreementService;
import io.renren.modules.resource.service.ResourcePoolService;
import io.renren.modules.resource.service.ResourceTradeMarkService;
import io.renren.modules.resource.vo.ResourceAgreementVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jinweia.wu
 * @create 2018-08-11 10:22
 **/
@RequestMapping("/api/resource/agreement")
@RestController
public class ResourceAgreementController {
    @Resource
    private ResourcePoolService resourcePoolService;
    @Resource
    private ResourceAgreementService resourceAgreementService;
    @Resource
    private ResourceTradeMarkService resourceTradeMarkService;
    @Resource
    private CommonService commonService;
    /**
     * 保存定时任务
     */
    @RequestMapping("/save")
    public R save(@LoginUser UserEntity user, @RequestBody ResourceAgreement resourceAgreement){
        ValidatorUtils.validateEntity(resourceAgreement);
        String username = user.getUsername();
        resourceAgreement.setCreateBy(username);
        resourceAgreement.setUpdateBy(username);
        resourceAgreement.setAdviser(username);
        resourceAgreement.setTypeDes(DistEnum.getDesByCode(resourceAgreement.getTypeCode()));
        resourceAgreement.setStatusCode(DistEnum.AGREEMENT_STATUS_SIGN.getTypeCode());
        resourceAgreement.setStatusDes(DistEnum.AGREEMENT_STATUS_SIGN.getDes());
        resourceAgreement.setUrgentCode(DistEnum.URGENT_NO.getTypeCode());
        resourceAgreement.setUrgentDes(DistEnum.URGENT_NO.getDes());
        resourceAgreementService.save(resourceAgreement);
        //更新资源表
        ResourcePoolModel resourcePoolModel = new ResourcePoolModel();
        resourcePoolModel.setUpdateBy(username);
        resourcePoolModel.setId(resourceAgreement.getResourceId());
        resourcePoolModel.setAgreementId(resourceAgreement.getAgreementId());
        resourcePoolModel.setStatusCode(DistEnum.RESOURCE_STATUS_SIGN_AGREEMENT.getTypeCode());
        resourcePoolModel.setStatusDes(DistEnum.RESOURCE_STATUS_SIGN_AGREEMENT.getDes());
        resourcePoolService.update(resourcePoolModel);
        return R.ok();
    }
    /**
     * 合同列表
     */
    @RequestMapping("/list")
    public R list(@LoginUser UserEntity user, @RequestParam(required = false) Map<String, Object> params) throws ParseException {
        //查询列表数据
        Object createStartDate = params.get("createStartDate");
        Object createEndDate = params.get("createEndDate");
        String username = user.getUsername();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(createStartDate!=null&&!"".equals(createStartDate.toString())){
             params.put("createStartDate", dateFormat.parse(createStartDate.toString()));
        }
        if(createEndDate!=null&&!"".equals(createEndDate.toString())){
            params.put("createEndDate",dateFormat.parse(createEndDate.toString()));
        }
        if(StringUtils.isNotBlank(params.get("statusCode").toString())){
            String[] statusCodes = params.get("statusCode").toString().split(",");
            params.put("statusCodeList",statusCodes);
        }
        params.put("adviser",username);
        Query query = new Query(params);
        List<ResourceAgreementVo> resourcePersonalPoolList = resourceAgreementService.queryList(query);
        int total = resourceAgreementService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(resourcePersonalPoolList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }
    /**
     * 合同列表
     */
    @RequestMapping("/get/{agreementId}")
    public R getAgreementById(@LoginUser UserEntity user,@PathVariable String agreementId) {
        //查询列表数据
        ResourceAgreement resourceAgreement = resourceAgreementService.getAgreementById(agreementId);
        return R.ok().put("data", resourceAgreement);
    }

    /**
     * 保存定时任务
     */
    @RequestMapping("/update")
    public R update(@LoginUser UserEntity user, @RequestBody ResourceAgreement resourceAgreement){
        ValidatorUtils.validateEntity(resourceAgreement);
        String username = user.getUsername();
        resourceAgreement.setUpdateBy(username);
        resourceAgreement.setStatusDes(DistEnum.getDesByCode(resourceAgreement.getStatusCode()));

        resourceAgreementService.update(resourceAgreement);
        return R.ok();
    }
    /**
     * 合同提交
     */
    @RequestMapping("/submit")
    public R submit(@LoginUser UserEntity user, @RequestParam String agreementId) throws Exception {
        ResourceAgreement resourceAgreementExist = resourceAgreementService.selectByPrimaryKey(agreementId);
        if(resourceAgreementExist==null||StringUtils.isBlank(resourceAgreementExist.getFinalFileName())){
            R.error("请确保提交合同前已上传甲方签字或盖章合同");
       }
        //修改数据库
        ResourceAgreement resourceAgreement = new ResourceAgreement();
        String username = user.getUsername();
        resourceAgreement.setAgreementId(agreementId);
        resourceAgreement.setUpdateBy(username);
        resourceAgreement.setStatusCode(DistEnum.AGREEMENT_STATUS_SUBMIT.getTypeCode());
        resourceAgreement.setStatusDes(DistEnum.AGREEMENT_STATUS_SUBMIT.getDes());
        resourceAgreementService.update(resourceAgreement);
        resourceTradeMarkService.updateStatusByAgreementId(agreementId);


        return R.ok();
    }
    /**
     * 生成合同pdf
     */
    @RequestMapping("/build/{operate}/{agreementId}")
    public R build(@LoginUser UserEntity user,@PathVariable String operate, @PathVariable String agreementId) throws Exception {
        ResourceAgreement resourceAgreement = new ResourceAgreement();
        String username = user.getUsername();
        resourceAgreement.setAgreementId(agreementId);
        resourceAgreement.setUpdateBy(username);
        //生成临时合同
        if(StringUtils.equals("temp",operate)){
            Map<String,String> agreementFormFieldMap = resourceAgreementService.selectFromByPrimaryKey(agreementId);
            //获取商标信息
            List<ResourceTradeMark> resourceTradeMarks = resourceTradeMarkService.selectByResourceId(agreementId);
            Map<String, String> pdfNameMap = commonService.manipulatePdf(agreementId,agreementFormFieldMap, resourceTradeMarks);
            //修改数据库
            resourceAgreement.setNonChapterFileName(pdfNameMap.get("nonChapterFileName"));
            resourceAgreement.setHasChapterFileName(pdfNameMap.get("hasChapterFileName"));
            resourceAgreementService.update(resourceAgreement);
        //生成最终合同
        }else if(StringUtils.equals("final",operate)){

        }
        return R.ok();
    }


}
