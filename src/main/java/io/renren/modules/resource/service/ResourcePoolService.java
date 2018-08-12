package io.renren.modules.resource.service;

import io.renren.modules.resource.dao.ResourcePoolMapper;
import io.renren.modules.resource.model.ResourcePersonalPoolModel;
import io.renren.modules.resource.model.ResourcePoolModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author jinweia.wu
 * @create 2018-08-12 23:12
 **/
@Service
public class ResourcePoolService {
    @Resource
    private ResourcePoolMapper resourcePoolMapper;
    public List<ResourcePersonalPoolModel> queryList(Map<String, Object> query){
        return resourcePoolMapper.queryList(query);
    }

    public int queryTotal(Map<String, Object> query) {
        return resourcePoolMapper.queryTotal(query);
    }

    public void save(ResourcePoolModel resourcePoolModel) {
        resourcePoolMapper.insert(resourcePoolModel);
    }

    public void update(ResourcePoolModel resourcePoolModel) {
        resourcePoolMapper.updateByPrimaryKeySelective(resourcePoolModel);
    }
}
