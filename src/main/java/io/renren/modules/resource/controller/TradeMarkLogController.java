package io.renren.modules.resource.controller;

import io.renren.common.constants.DistEnum;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.api.annotation.LoginUser;
import io.renren.modules.api.entity.UserEntity;
import io.renren.modules.resource.model.ResourceTradeMark;
import io.renren.modules.resource.model.TradeMarkLog;
import io.renren.modules.resource.service.ResourceTradeMarkService;
import io.renren.modules.resource.service.TradeMarkLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/tradeMark/log")
@RestController
public class TradeMarkLogController {
    @Resource
    private TradeMarkLogService tradeMarkLogService;
    @Resource
    private ResourceTradeMarkService resourceTradeMarkService;

    /**
     * 商标日志保存
     */
    @RequestMapping("/save")
    public R save(@LoginUser UserEntity user, @RequestBody TradeMarkLog tradeMarkLog){
        ValidatorUtils.validateEntity(tradeMarkLog);
        String username = user.getUsername();
        tradeMarkLog.setId(UUID.randomUUID().toString().replaceAll("-",""));
        tradeMarkLog.setCreateBy(username);
        tradeMarkLog.setStatusDes(DistEnum.getDesByCode(tradeMarkLog.getStatusCode()));
        tradeMarkLog.setOperationDes(DistEnum.getDesByCode(tradeMarkLog.getOperationCode()));
        tradeMarkLogService.save(tradeMarkLog);
        //修改商标库
        if(StringUtils.isNotBlank(tradeMarkLog.getStatusCode())){
            ResourceTradeMark resourceTradeMark = new ResourceTradeMark();
            resourceTradeMark.setId(tradeMarkLog.getTradeMarkId());
            resourceTradeMark.setStatusCode(tradeMarkLog.getStatusCode());
            resourceTradeMark.setStatusDes(DistEnum.getDesByCode(tradeMarkLog.getStatusCode()));
            if(tradeMarkLog.getStatusCode().equals(DistEnum.TRADE_MARK_STATUS_APPLY.getTypeCode())){
                resourceTradeMark.setApplyBy(username);
                resourceTradeMark.setApplyDate(new Date());
                resourceTradeMark.setApplyNumber(tradeMarkLog.getApplyNumber());
            }

            resourceTradeMarkService.update(resourceTradeMark);
        }
        return R.ok();
    }
    /**
     * 商标日志保存
     */
    @RequestMapping("/list/{id}")
    public R list(@LoginUser UserEntity user, @PathVariable String id){
        List<TradeMarkLog> tradeMarkLogs = tradeMarkLogService.selectByTradeMarkId(id);
        return R.ok().put("data",tradeMarkLogs);
    }
}
