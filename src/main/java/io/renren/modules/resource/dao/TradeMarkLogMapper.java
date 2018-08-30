package io.renren.modules.resource.dao;

import io.renren.modules.resource.model.TradeMarkLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeMarkLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(TradeMarkLog record);

    int insertSelective(TradeMarkLog record);

    TradeMarkLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TradeMarkLog record);

    int updateByPrimaryKey(TradeMarkLog record);

    List<TradeMarkLog> selectByTradeMarkId(String tradeMarkId);
}