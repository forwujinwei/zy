package io.renren.modules.resource.service;

import io.renren.common.utils.Query;
import io.renren.modules.resource.dao.ToDoListMapper;
import io.renren.modules.resource.model.ToDoList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ToDoListService {
    @Resource
    private ToDoListMapper toDoListMapper;


    public List<ToDoList> queryList(Query query) {
        return toDoListMapper.queryList(query);
    }

    public int queryTotal(Query query) {
        return toDoListMapper.queryTotal(query);
    }

    public void save(ToDoList toDoList) {
        toDoListMapper.insert(toDoList);
    }
}
