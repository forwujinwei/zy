package io.renren.modules.resource.service;

import io.renren.common.utils.Query;
import io.renren.modules.resource.dao.ResourceAgreementMapper;
import io.renren.modules.resource.model.ResourceAgreement;
import io.renren.modules.resource.vo.ResourceAgreementVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author jinweia.wu
 * @create 2018-08-11 10:18
 **/
@Service
public class ResourceAgreementService {
    @Resource
    private ResourceAgreementMapper resourceAgreementMapper;

   public List<ResourceAgreementVo> queryList(Map<String, Object> query){
        return resourceAgreementMapper.queryList(query);
    }

    public int queryTotal(Map<String, Object> query) {
        return resourceAgreementMapper.queryTotal(query);
    }

    public void save(ResourceAgreement resourceAgreement) {
        resourceAgreementMapper.insert(resourceAgreement);
    }

    public void update(ResourceAgreement resourceAgreement) {
        resourceAgreementMapper.updateByPrimaryKeySelective(resourceAgreement);
    }

    public ResourceAgreement selectByPrimaryKey(String agreementId) {
        return resourceAgreementMapper.selectByPrimaryKey(agreementId);

    }

    public Map<String, String> selectFromByPrimaryKey(String agreementId) {

        return resourceAgreementMapper.selectFromByPrimaryKey(agreementId);
    }

    public ResourceAgreement getAgreementById(String agreementId) {
        return resourceAgreementMapper.selectByPrimaryKey(agreementId);
    }

    public List<ResourceAgreementVo> queryAgreementManageList(Query query) {
        return resourceAgreementMapper.queryAgreementManageList(query);

    }

    public int queryAgreementManageListTotal(Query query) {
        return resourceAgreementMapper.queryAgreementManageListTotal(query);
    }
}
