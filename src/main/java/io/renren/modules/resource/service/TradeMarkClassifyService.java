package io.renren.modules.resource.service;

import io.renren.modules.resource.dao.TradeMarkClassifyMapper;
import io.renren.modules.resource.model.TradeMarkClassify;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jinweia.wu
 * @create 2018-08-12 12:31
 **/
@Service
public class TradeMarkClassifyService {
    @Resource
    private TradeMarkClassifyMapper tradeMarkClassifyMapper;

    public void save(TradeMarkClassify tradeMarkClassify){
        tradeMarkClassifyMapper.insert(tradeMarkClassify);
    }
}
