package io.renren.modules.resource.dao;

import io.renren.common.utils.Query;
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

    int queryTotal(Map<String, Object> query);

    List<ResourceTradeMark> queryList(Map<String, Object> query);

    void updateStatusByAgreementId(String agreementId);

    List<ResourceTradeMark> selectByResourceId(String agreementId);

    List<ResourceTradeMark> queryManageList(Query query);

    int queryManageListTotal(Query query);

    List<ResourceTradeMark> queryListByAgreementId(Query query);

    int queryListByAgreementIdTotal(Query query);
}