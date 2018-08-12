package io.renren.modules.resource.controller;

import io.renren.common.constants.DistEnum;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.api.annotation.AuthIgnore;
import io.renren.modules.api.annotation.LoginUser;
import io.renren.modules.api.entity.UserEntity;
import io.renren.modules.resource.model.ResourcePublicPool;
import io.renren.modules.resource.service.ResourcePublicPoolService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 资源公共池
 *
 * @author jinweia.wu
 * @create 2018-08-12 16:33
 **/
@RequestMapping("/api/resource/public")
@RestController
public class ResourcePublicPoolController {
    @Resource
    private ResourcePublicPoolService resourcePublicPoolService;

    @AuthIgnore
    @RequestMapping("/list")
    public R list(@RequestParam(required = false) Map<String, Object> params){
        params.put("statusCode",DistEnum.RESOURCE_STATUS_NO_SURE.getTypeCode());
        Query query = new Query(params);
        List<ResourcePublicPool> resourcePublicPools = resourcePublicPoolService.queryList(query);
        int total = resourcePublicPoolService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(resourcePublicPools, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 保存定时任务
     */
    @RequestMapping("/save")
    public R save(@LoginUser UserEntity user, @RequestBody ResourcePublicPool resourcePublicPool){
        ValidatorUtils.validateEntity(resourcePublicPool);
        String username = user.getUsername();
        resourcePublicPool.setId(UUID.randomUUID().toString().replaceAll("-",""));
        resourcePublicPool.setCreateBy(username);
        resourcePublicPool.setUpdateBy(username);
        resourcePublicPool.setStatusCode(DistEnum.RESOURCE_STATUS_NO_SURE.getTypeCode());
        resourcePublicPool.setStatusDes(DistEnum.RESOURCE_STATUS_NO_SURE.getDes());
        resourcePublicPool.setSourceDes(DistEnum.getDesByCode(resourcePublicPool.getSourceCode()));
        resourcePublicPool.setTypeDes(DistEnum.getDesByCode(resourcePublicPool.getTypeCode()));
        resourcePublicPool.setAcrossCode(DistEnum.ACROSS_TYPE_HOME_STATION.getTypeCode());
        resourcePublicPool.setAcrossDes(DistEnum.ACROSS_TYPE_HOME_STATION.getDes());
        resourcePublicPool.setSubmitBy(username);
        resourcePublicPool.setSubmitDate(new Date());
        resourcePublicPoolService.save(resourcePublicPool);
        return R.ok();
    }
}
