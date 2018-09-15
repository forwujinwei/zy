package io.renren.modules.resource.dao;

import io.renren.common.utils.Query;
import io.renren.modules.resource.model.ToDoList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ToDoListMapper {
    int deleteByPrimaryKey(String id);

    int insert(ToDoList record);

    int insertSelective(ToDoList record);

    ToDoList selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ToDoList record);

    int updateByPrimaryKey(ToDoList record);

    int queryTotal(Query query);

    List<ToDoList> queryList(Query query);
}