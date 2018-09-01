package io.renren.modules.resource.controller;

import io.renren.common.constants.DistEnum;
import io.renren.common.utils.CommonMethod;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.api.annotation.LoginUser;
import io.renren.modules.api.entity.UserEntity;
import io.renren.modules.resource.model.ResourceTradeMark;
import io.renren.modules.resource.service.ResourceTradeMarkService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商标管理
 *
 * @author jinweia.wu
 * @create 2018-08-18 10:36
 **/
@RequestMapping("/api/resource/trademark")
@RestController
public class ResourceTradeMarkController {
    @Resource
    private ResourceTradeMarkService resourceTradeMarkService;
    /**
     * 保存商标
     */
    @RequestMapping("/save")
    public R save(@LoginUser UserEntity user, @RequestBody ResourceTradeMark resourceTradeMark){
        ValidatorUtils.validateEntity(resourceTradeMark);
        String username = user.getUsername();
        resourceTradeMark.setId(CommonMethod.getUUID());
        resourceTradeMark.setClassifyDes(DistEnum.getDesByCode(resourceTradeMark.getClassifyCode()));
        resourceTradeMark.setDocCode(DistEnum.TRADE_MARK_DOC_TYPE_001.getTypeCode());
        resourceTradeMark.setDocDes(DistEnum.TRADE_MARK_DOC_TYPE_001.getDes());
        resourceTradeMark.setStatusCode(DistEnum.TRADE_MARK_STATUS_DRAFT.getTypeCode());
        resourceTradeMark.setStatusDes(DistEnum.TRADE_MARK_STATUS_DRAFT.getDes());
        resourceTradeMark.setApplyTypeDes(DistEnum.getDesByCode(resourceTradeMark.getApplyTypeCode()));
        resourceTradeMark.setCreateBy(username);
        resourceTradeMark.setUpdateBy(username);
        resourceTradeMark.setAdviser(username);
        resourceTradeMark.setDeleteFlag("0");
        resourceTradeMarkService.save(resourceTradeMark);
        return R.ok();
    }
    /**
     * 商标列表
     */
    @RequestMapping("/list")
    public R list(@LoginUser UserEntity user, @RequestParam(required = false) Map<String, Object> params) {
        //查询列表数据
        String username = user.getUsername();
        params.put("adviser",username);
        Query query = new Query(params);
        List<ResourceTradeMark> resourceTradeMarks = resourceTradeMarkService.queryList(query);
        int total = resourceTradeMarkService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(resourceTradeMarks, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 商标列表
     */
    @RequestMapping("/{id}")
    public R findById(@LoginUser UserEntity user, @PathVariable String id) {
        //查询列表数据
       /* String username = user.getUsername();*/
        ResourceTradeMark resourceTradeMarks = resourceTradeMarkService.findById(id);
        return R.ok().put("data", resourceTradeMarks);
    }

    /**
     * 商标保存
     */
    @RequestMapping("/update")
    public R update(@LoginUser UserEntity user, @RequestBody ResourceTradeMark resourceTradeMark){
        ValidatorUtils.validateEntity(resourceTradeMark);
        String username = user.getUsername();
        resourceTradeMark.setUpdateBy(username);
        if(StringUtils.isNotBlank(resourceTradeMark.getClassifyCode())){
            resourceTradeMark.setClassifyDes(DistEnum.getDesByCode(resourceTradeMark.getClassifyCode()));
        }
        if(StringUtils.isNotBlank(resourceTradeMark.getDocCode())){
            resourceTradeMark.setDocDes(DistEnum.getDesByCode(resourceTradeMark.getDocCode()));
        }

        resourceTradeMarkService.update(resourceTradeMark);
        return R.ok();
    }

    /**
     * 商标保存
     */
    @RequestMapping("/delete/{id}")
    public R update(@LoginUser UserEntity user,@PathVariable String id){
        String username = user.getUsername();
        ResourceTradeMark resourceTradeMark = new ResourceTradeMark();
        resourceTradeMark.setId(id);
        resourceTradeMark.setUpdateBy(username);
        resourceTradeMark.setDeleteFlag("1");
        resourceTradeMarkService.update(resourceTradeMark);
        return R.ok();
    }
}
