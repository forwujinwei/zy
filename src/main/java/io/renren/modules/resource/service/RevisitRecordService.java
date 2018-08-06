package io.renren.modules.resource.service;

import io.renren.modules.resource.dao.RevisitRecordMapper;
import io.renren.modules.resource.model.RevisitRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author jinweia.wu
 * @create 2018-08-05 10:44
 **/
@Service
public class RevisitRecordService {
    @Resource
    private RevisitRecordMapper revisitRecordMapper;


    public List<RevisitRecord> queryList(Map<String,Object> query) {
        return revisitRecordMapper.queryList(query);
    }

    public int queryTotal(Map<String,Object> query) {
        return revisitRecordMapper.queryTotal(query);
    }

    public int save(RevisitRecord revisitRecord) {
        return revisitRecordMapper.insert(revisitRecord);
    }
}
