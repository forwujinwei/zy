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
import io.renren.modules.resource.model.RevisitRecord;
import io.renren.modules.resource.service.ResourcePersonalPoolService;
import io.renren.modules.resource.service.RevisitRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 回访记录
 *
 * @author jinweia.wu
 * @create 2018-08-05 10:43
 **/
@RequestMapping("/api/revisit")
@RestController
public class RevisitRecordController {
    @Resource
    private RevisitRecordService revisitRecordService;
    @Resource
    private ResourcePersonalPoolService resourcePersonalPoolService;

    @AuthIgnore
    @RequestMapping("/list/{resourceId}")
    public R list(@PathVariable("resourceId") String resourceId, @RequestParam Map<String, Object> params){
        //查询列表数据
        params.put("resourceId",resourceId);
        Query query = new Query(params);
        List<RevisitRecord> resourcePersonalPoolList = revisitRecordService.queryList(query);
        int total = revisitRecordService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(resourcePersonalPoolList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 保存定时任务
     */
    @RequestMapping("/save")
    public R save(@LoginUser UserEntity user, @RequestBody Map<String,String> param) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String username = user.getUsername();
        String resourceId = param.get("resourceId");
        String intentCode = param.get("intentCode");
        Date revisitDate = dateFormat.parse(param.get("revisitDate"));
        String revisitRemark = param.get("revisitRemark");
        RevisitRecord revisitRecord = new RevisitRecord();
        revisitRecord.setId(UUID.randomUUID().toString().replaceAll("-",""));
        revisitRecord.setIntentCode(intentCode);
        revisitRecord.setIntentDes(DistEnum.getDesByCode(revisitRecord.getIntentCode()));
        revisitRecord.setRevisitDate(revisitDate);
        revisitRecord.setRevisitRemark(revisitRemark);
        revisitRecord.setIntentDes(DistEnum.getDesByCode(intentCode));
        revisitRecord.setAdviser(username);
        revisitRecord.setCreateBy(username);
        revisitRecord.setResourceId(resourceId);
        revisitRecordService.save(revisitRecord);
        ResourcePersonalPoolModel resourcePersonalPoolModel = new ResourcePersonalPoolModel();
        resourcePersonalPoolModel.setId(resourceId);
        resourcePersonalPoolModel.setIntentCode(intentCode);
        resourcePersonalPoolModel.setIntentDes(DistEnum.getDesByCode(intentCode));
        resourcePersonalPoolModel.setEndRevisitDate(revisitDate);
        resourcePersonalPoolModel.setUpdateBy(username);
        resourcePersonalPoolService.update(resourcePersonalPoolModel);
        return R.ok();
    }

}
