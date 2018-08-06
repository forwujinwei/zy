package io.renren.generation.dao;

import io.renren.generation.model.RevisitRecord;

public interface RevisitRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(RevisitRecord record);

    int insertSelective(RevisitRecord record);

    RevisitRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RevisitRecord record);

    int updateByPrimaryKey(RevisitRecord record);
}