package io.renren.modules.resource.dao;

import io.renren.modules.resource.model.ResourcePersonalPoolModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface ResourcePersonalPoolMapper {
    int deleteByPrimaryKey(String id);

    int insert(ResourcePersonalPoolModel record);

    int insertSelective(ResourcePersonalPoolModel record);

    ResourcePersonalPoolModel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResourcePersonalPoolModel record);

    int updateByPrimaryKey(ResourcePersonalPoolModel record);

    List<ResourcePersonalPoolModel> queryList(Map<String, Object> query);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);
}