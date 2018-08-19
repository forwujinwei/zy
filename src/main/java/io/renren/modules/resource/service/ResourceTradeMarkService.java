package io.renren.modules.resource.service;

import io.renren.modules.resource.dao.ResourcePoolMapper;
import io.renren.modules.resource.dao.ResourceTradeMarkMapper;
import io.renren.modules.resource.model.ResourcePersonalPoolModel;
import io.renren.modules.resource.model.ResourcePoolModel;
import io.renren.modules.resource.model.ResourceTradeMark;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 商标管理
 *
 * @author jinweia.wu
 * @create 2018-08-17 23:56
 **/
@Service
public class ResourceTradeMarkService {
    @Resource
    private ResourceTradeMarkMapper resourceTradeMarkMapper;
    public List<ResourceTradeMark> queryList(Map<String, Object> query){
        return resourceTradeMarkMapper.queryList(query);
    }

    public int queryTotal(Map<String, Object> query) {
        return resourceTradeMarkMapper.queryTotal(query);
    }


    public void save(ResourceTradeMark resourceTradeMark) {
        resourceTradeMarkMapper.insert(resourceTradeMark);
    }

    public void update(ResourceTradeMark resourceTradeMark) {
        resourceTradeMarkMapper.updateByPrimaryKeySelective(resourceTradeMark);
    }

    public ResourceTradeMark findById(String id) {
        return resourceTradeMarkMapper.selectByPrimaryKey(id);
    }
}
