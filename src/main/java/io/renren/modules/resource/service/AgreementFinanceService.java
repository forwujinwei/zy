package io.renren.modules.resource.service;

import io.renren.common.utils.Query;
import io.renren.modules.resource.dao.AgreementFinanceMapper;
import io.renren.modules.resource.model.AgreementFinanceModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AgreementFinanceService {
    @Resource
    private AgreementFinanceMapper agreementFinanceMapper;


    public List<AgreementFinanceModel> queryList(Query query) {
        return agreementFinanceMapper.queryList(query);
    }

    public int queryTotal(Query query) {
        return agreementFinanceMapper.queryTotal(query);
    }

    public void save(AgreementFinanceModel agreementFinanceModel) {
        agreementFinanceMapper.insert(agreementFinanceModel);
    }

    public void update(AgreementFinanceModel agreementFinanceModel) {
        agreementFinanceMapper.updateByPrimaryKeySelective(agreementFinanceModel);
    }
}
