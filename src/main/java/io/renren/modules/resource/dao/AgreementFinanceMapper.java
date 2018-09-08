package io.renren.modules.resource.dao;

import io.renren.common.utils.Query;
import io.renren.modules.resource.model.AgreementFinanceModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AgreementFinanceMapper {
    int deleteByPrimaryKey(String agreementId);

    int insert(AgreementFinanceModel record);

    int insertSelective(AgreementFinanceModel record);

    AgreementFinanceModel selectByPrimaryKey(String agreementId);

    int updateByPrimaryKeySelective(AgreementFinanceModel record);

    int updateByPrimaryKey(AgreementFinanceModel record);

    List<AgreementFinanceModel> queryList(Query query);

    int queryTotal(Query query);

}