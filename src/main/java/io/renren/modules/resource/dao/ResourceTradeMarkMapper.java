package io.renren.modules.resource.dao;

import io.renren.modules.resource.model.ResourceTradeMark;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface ResourceTradeMarkMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResourceTradeMark record);

    int insertSelective(ResourceTradeMark record);

    ResourceTradeMark selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResourceTradeMark record);

    int updateByPrimaryKey(ResourceTradeMark record);

    List<ResourceTradeMark> queryList(Map<String, Object> query);

    int queryTotal(Map<String, Object> query);

}