package io.renren.modules.resource.service;

import io.renren.modules.resource.dao.ResourcePublicPoolMapper;
import io.renren.modules.resource.model.ResourcePublicPool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 资源公共池
 *
 * @author jinweia.wu
 * @create 2018-08-12 16:34
 **/
@Service
public class ResourcePublicPoolService {
    @Resource
    private ResourcePublicPoolMapper resourcePublicPoolMapper;
    public List<ResourcePublicPool> queryList(Map<String, Object> query){
        return resourcePublicPoolMapper.queryList(query);
    }

    public int queryTotal(Map<String, Object> query) {
        return resourcePublicPoolMapper.queryTotal(query);
    }

    public void save(ResourcePublicPool resourcePublicPool) {
        resourcePublicPoolMapper.insert(resourcePublicPool);
    }

    public void update(ResourcePublicPool resourcePublicPool) {
        resourcePublicPoolMapper.updateByPrimaryKeySelective(resourcePublicPool);
    }
}
