package io.renren.modules.resource.service;

import io.renren.modules.resource.dao.ResourcePersonalPoolMapper;
import io.renren.modules.resource.model.ResourcePersonalPoolModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author jinweia.wu
 * @create 2018-08-04 10:48
 **/
@Service
public class ResourcePersonalPoolService {
    @Resource
    private ResourcePersonalPoolMapper resourcePersonalPoolMapper;

    public List<ResourcePersonalPoolModel> queryList(Map<String, Object> query){
        return resourcePersonalPoolMapper.queryList(query);
    }

    public int queryTotal(Map<String, Object> query) {
        return resourcePersonalPoolMapper.queryTotal(query);
    }

    public void save(ResourcePersonalPoolModel resourcePersonalPoolModel) {
        resourcePersonalPoolMapper.insert(resourcePersonalPoolModel);
    }

    public void update(ResourcePersonalPoolModel resourcePersonalPoolModel) {
        resourcePersonalPoolMapper.updateByPrimaryKeySelective(resourcePersonalPoolModel);
    }
}
