package io.renren.modules.resource.dao;

import io.renren.modules.resource.model.ResourceAgreement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ResourceAgreementMapper {
    int deleteByPrimaryKey(String agreementId);

    int insert(ResourceAgreement record);

    int insertSelective(ResourceAgreement record);

    ResourceAgreement selectByPrimaryKey(String agreementId);

    int updateByPrimaryKeySelective(ResourceAgreement record);

    int updateByPrimaryKey(ResourceAgreement record);

    List<ResourceAgreement> queryList(Map<String, Object> query);

    int queryTotal(Map<String, Object> query);
}