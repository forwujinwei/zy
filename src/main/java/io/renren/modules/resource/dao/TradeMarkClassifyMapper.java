package io.renren.modules.resource.dao;

import io.renren.modules.resource.model.TradeMarkClassify;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeMarkClassifyMapper {
    int insert(TradeMarkClassify record);

    int insertSelective(TradeMarkClassify record);
}