package io.renren.modules.resource.dao;

import io.renren.common.utils.Query;
import io.renren.modules.resource.model.AgreementFinanceModel;
import io.renren.modules.resource.vo.AgreementFinanceVo;
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

    List<AgreementFinanceVo> queryList(Query query);

    int queryTotal(Query query);

    List<AgreementFinanceVo> queryFileList(Query query);

    int queryFileListTotal(Query query);
}