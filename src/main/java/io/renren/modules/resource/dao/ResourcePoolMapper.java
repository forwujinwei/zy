package io.renren.modules.resource.dao;

import io.renren.modules.resource.model.ResourcePersonalPoolModel;
import io.renren.modules.resource.model.ResourcePoolModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ResourcePoolMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResourcePoolModel record);

    int insertSelective(ResourcePoolModel record);

    ResourcePoolModel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResourcePoolModel record);

    int updateByPrimaryKey(ResourcePoolModel record);

    List<ResourcePersonalPoolModel> queryList(Map<String, Object> query);

    int queryTotal(Map<String, Object> query);
}