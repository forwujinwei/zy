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
import io.renren.modules.resource.model.ResourcePoolModel;
import io.renren.modules.resource.service.ResourcePoolService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author jinweia.wu
 * @create 2018-08-12 23:13
 **/

@RequestMapping("/api/resource")
@RestController
public class ResourcePoolController {

    @Resource
    private ResourcePoolService resourcePoolService;
    @AuthIgnore
    @RequestMapping("/{dataSourceCode}/list/{statusCode}")
    public R list(@PathVariable String dataSourceCode,@PathVariable String statusCode, @RequestParam Map<String, Object> params) throws ParseException {
        //查询列表数据
        params.put("dataSourceCode",dataSourceCode);
        params.put("statusCode",statusCode);
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
    public R save(@LoginUser UserEntity user, @RequestBody ResourcePoolModel resourcePoolModel){
        ValidatorUtils.validateEntity(resourcePoolModel);
        String username = user.getUsername();
        resourcePoolModel.setId(UUID.randomUUID().toString().replaceAll("-",""));
        resourcePoolModel.setCreateBy(username);
        resourcePoolModel.setUpdateBy(username);
        resourcePoolModel.setAdviser(username);
        resourcePoolModel.setStatusCode(DistEnum.RESOURCE_STATUS_NO_SURE.getTypeCode());
        resourcePoolModel.setStatusDes(DistEnum.RESOURCE_STATUS_NO_SURE.getDes());
        resourcePoolModel.setSourceDes(DistEnum.getDesByCode(resourcePoolModel.getSourceCode()));
        resourcePoolModel.setTypeDes(DistEnum.getDesByCode(resourcePoolModel.getTypeCode()));
        resourcePoolModel.setAcrossCode(DistEnum.ACROSS_TYPE_HOME_STATION.getTypeCode());
        resourcePoolModel.setAcrossDes(DistEnum.ACROSS_TYPE_HOME_STATION.getDes());
        resourcePoolModel.setSubmitBy(username);
        resourcePoolModel.setSubmitDate(new Date());
        resourcePoolModel.setSureBy(username);
        resourcePoolModel.setSureDate(new Date());
        resourcePoolService.save(resourcePoolModel);
        return R.ok();
    }

    /**
     * 更新人工导入数据状态
     * optoonal:cancel(放弃)，sure（确认）
     */
    @RequestMapping("/{dataSourceCode}/update/{optional}")
    public R update(@LoginUser UserEntity user,@PathVariable String dataSourceCode,@PathVariable String optional, @RequestBody ResourcePoolModel resourcePoolModel){
        ValidatorUtils.validateEntity(resourcePoolModel);
        String username = user.getUsername();
        if(DistEnum.RESOURCE_DATA_SOURCE_PERSONAL.getTypeCode().equalsIgnoreCase(dataSourceCode)){//人工导入
            if("cancel".equals(optional)){
                return R.error("人工导入数据不允许放弃");
            }else if("sure".equals(optional)){
                resourcePoolModel.setAdviser(username);
                resourcePoolModel.setStatusCode(DistEnum.RESOURCE_STATUS_SURE.getTypeCode());
                resourcePoolModel.setStatusDes(DistEnum.RESOURCE_STATUS_SURE.getDes());
                resourcePoolModel.setSureBy(username);
                resourcePoolModel.setSureDate(new Date());
                resourcePoolModel.setUpdateBy(username);
                resourcePoolService.update(resourcePoolModel);
            }
        }else if(DistEnum.RESOURCE_DATA_SOURCE_REAL_TIME.getTypeCode().equalsIgnoreCase(dataSourceCode)){//实时数据
            if("cancel".equals(optional)){
                resourcePoolModel.setStatusCode(DistEnum.RESOURCE_STATUS_GIVE_UP.getTypeCode());
                resourcePoolModel.setStatusDes(DistEnum.RESOURCE_STATUS_GIVE_UP.getDes());
                resourcePoolModel.setCancelBy(username);
                resourcePoolModel.setCancelDate(new Date());
                resourcePoolModel.setUpdateBy(username);
                resourcePoolService.update(resourcePoolModel);
            }else if("sure".equals(optional)){
                resourcePoolModel.setAdviser(username);
                resourcePoolModel.setStatusCode(DistEnum.RESOURCE_STATUS_SURE.getTypeCode());
                resourcePoolModel.setStatusDes(DistEnum.RESOURCE_STATUS_SURE.getDes());
                resourcePoolModel.setSureBy(username);
                resourcePoolModel.setSureDate(new Date());
                resourcePoolModel.setUpdateBy(username);
                resourcePoolService.update(resourcePoolModel);
            }
        }
        return R.ok();
    }
}
