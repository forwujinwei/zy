package io.renren.modules.resource.controller;

import io.renren.common.constants.DistEnum;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.api.annotation.AuthIgnore;
import io.renren.modules.api.annotation.LoginUser;
import io.renren.modules.api.entity.UserEntity;
import io.renren.modules.resource.model.ResourcePersonalPoolModel;
import io.renren.modules.resource.service.ResourcePersonalPoolService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jinweia.wu
 * @create 2018-08-04 10:47
 **/
@RequestMapping("/api/resource")
@RestController
public class ResourcePersonalPoolController {
    @Resource
    private ResourcePersonalPoolService resourcePoolService;
    @AuthIgnore
    @RequestMapping("/list")
    public R list(@RequestParam(required = false) Map<String, Object> params) throws ParseException {
        //查询列表数据
        Object submitStartDate = params.get("submitStartDate");
        Object submitEndDate = params.get("submitEndDate");
        Object sureStartDate = params.get("sureStartDate");
        Object sureEndDate = params.get("sureEndDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(submitStartDate!=null&&!"".equals(submitStartDate.toString())){
            params.put("submitStartDate",dateFormat.parse(submitStartDate.toString()));
        }
        if(submitEndDate!=null&&!"".equals(submitEndDate.toString())){
            params.put("submitEndDate",dateFormat.parse(submitEndDate.toString()));
        }
        if(sureStartDate!=null&&!"".equals(sureStartDate.toString())){
            params.put("sureStartDate",dateFormat.parse(sureStartDate.toString()));
        }
        if(sureEndDate!=null&&!"".equals(sureEndDate.toString())){
            params.put("sureEndDate",dateFormat.parse(sureEndDate.toString()));
        }
        Query query = new Query(params);
        List<ResourcePersonalPoolModel> resourcePersonalPoolList = resourcePoolService.queryList(query);
        int total = resourcePoolService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(resourcePersonalPoolList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 保存定时任务
     */
    @RequestMapping("/save")
    public R save(@LoginUser UserEntity user, @RequestBody ResourcePersonalPoolModel resourcePersonalPoolModel){
        ValidatorUtils.validateEntity(resourcePersonalPoolModel);
        String username = user.getUsername();
        resourcePersonalPoolModel.setId(UUID.randomUUID().toString().replaceAll("-",""));
        resourcePersonalPoolModel.setCreateBy(username);
        resourcePersonalPoolModel.setUpdateBy(username);
        resourcePersonalPoolModel.setAdviser(username);
        resourcePersonalPoolModel.setStatusCode(DistEnum.RESOURCE_STATUS_NO_SURE.getTypeCode());
        resourcePersonalPoolModel.setStatusDes(DistEnum.RESOURCE_STATUS_NO_SURE.getDes());
        resourcePersonalPoolModel.setIntentCode(DistEnum.INTENT_TOBE_CONFIRM.getTypeCode());
        resourcePersonalPoolModel.setIntentDes(DistEnum.INTENT_TOBE_CONFIRM.getDes());
        resourcePersonalPoolModel.setSourceDes(DistEnum.getDesByCode(resourcePersonalPoolModel.getSourceCode()));
        resourcePersonalPoolModel.setTypeDes(DistEnum.getDesByCode(resourcePersonalPoolModel.getTypeCode()));
        resourcePersonalPoolModel.setAcrossCode(DistEnum.ACROSS_TYPE_HOME_STATION.getTypeCode());
        resourcePersonalPoolModel.setAcrossDes(DistEnum.ACROSS_TYPE_HOME_STATION.getDes());
        resourcePersonalPoolModel.setSubmitBy(username);
        resourcePersonalPoolModel.setSubmitDate(new Date());
        resourcePersonalPoolModel.setSureBy(username);
        resourcePersonalPoolModel.setSureDate(new Date());
        resourcePoolService.save(resourcePersonalPoolModel);
        return R.ok();
    }
}
