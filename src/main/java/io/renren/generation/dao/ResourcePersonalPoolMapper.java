package io.renren.generation.dao;

import io.renren.generation.model.ResourcePersonalPool;

public interface ResourcePersonalPoolMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResourcePersonalPool record);

    int insertSelective(ResourcePersonalPool record);

    ResourcePersonalPool selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResourcePersonalPool record);

    int updateByPrimaryKey(ResourcePersonalPool record);
}