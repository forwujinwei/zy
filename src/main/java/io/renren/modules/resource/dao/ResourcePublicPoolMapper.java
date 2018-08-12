package io.renren.modules.resource.dao;

import io.renren.modules.resource.model.ResourcePublicPool;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ResourcePublicPoolMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResourcePublicPool record);

    int insertSelective(ResourcePublicPool record);

    ResourcePublicPool selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResourcePublicPool record);

    int updateByPrimaryKey(ResourcePublicPool record);

    List<ResourcePublicPool> queryList(Map<String, Object> query);

    int queryTotal(Map<String, Object> query);
}