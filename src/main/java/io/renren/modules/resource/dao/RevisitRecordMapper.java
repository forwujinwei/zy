package io.renren.modules.resource.dao;

import io.renren.modules.resource.model.RevisitRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface RevisitRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(RevisitRecord record);

    int insertSelective(RevisitRecord record);

    RevisitRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RevisitRecord record);

    int updateByPrimaryKey(RevisitRecord record);

    List<RevisitRecord> queryList(Map<String, Object> query);

    int queryTotal(Map<String, Object> query);
}