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
import io.renren.modules.resource.service.ResourceAgreementService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private ResourceAgreementService resourceAgreementService;
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
        return R.ok();
    }
    /**
     * 保存定时任务
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
        params.put("username",username);
        Query query = new Query(params);
        List<ResourceAgreement> resourcePersonalPoolList = resourceAgreementService.queryList(query);
        int total = resourceAgreementService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(resourcePersonalPoolList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
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
}
