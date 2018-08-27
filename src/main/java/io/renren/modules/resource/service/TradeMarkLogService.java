package io.renren.modules.resource.service;

import io.renren.modules.resource.dao.TradeMarkLogMapper;
import io.renren.modules.resource.model.TradeMarkLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TradeMarkLogService {
    @Resource
    private TradeMarkLogMapper tradeMarkLogMapper;
    public void save(TradeMarkLog tradeMarkLog) {
        tradeMarkLogMapper.insert(tradeMarkLog);
    }


    public List<TradeMarkLog> selectByTradeMarkId(String tradeMarkId){
        return tradeMarkLogMapper.selectByTradeMarkId(tradeMarkId);
    }
}
