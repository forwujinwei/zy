package io.renren.modules;

import io.renren.common.constants.DistEnum;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.api.annotation.LoginUser;
import io.renren.modules.api.entity.UserEntity;
import io.renren.modules.resource.model.ResourcePersonalPoolModel;
import io.renren.modules.resource.model.ToDoList;
import io.renren.modules.resource.service.ToDoListService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/api/todo")
@RestController
public class ToDoListController {
    @Resource
    private ToDoListService toDoListService;

    @RequestMapping("/list")
    public R list(@LoginUser UserEntity user, @RequestParam Map<String, Object> params)  {
        //查询列表数据
        String username = user.getUsername();
        if(StringUtils.isBlank(username)){
            return R.error("用户不存在，请联系管理员");
        }
        params.put("username",username);
        Query query = new Query(params);

        List<ToDoList> toDoLists = toDoListService.queryList(query);
        int total = toDoListService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(toDoLists, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    @RequestMapping("/save")
    public R save(@LoginUser UserEntity user, @RequestBody ToDoList toDoList)  {
        //查询列表数据
        String username = user.getUsername();
        if(StringUtils.isBlank(username)){
            return R.error("用户不存在，请联系管理员");
        }
        toDoList.setStatus(DistEnum.TODO_STATUS_001.getTypeCode());
        toDoList.setId(UUID.randomUUID().toString().replaceAll("-",""));
        toDoList.setCreateDate(new Date());
        toDoList.setCreateBy(username);
        toDoList.setUpdateDate(new Date());
        toDoList.setDeleteFlag("0");
        toDoListService.save(toDoList);
        return R.ok();
    }

    @RequestMapping("/update/{id}")
    public R save(@LoginUser UserEntity user, @RequestParam String  status,@PathVariable String  id)  {
        //查询列表数据
        String username = user.getUsername();
        if(StringUtils.isBlank(username)){
            return R.error("用户不存在，请联系管理员");
        }
        ToDoList toDoList = new ToDoList();
        toDoList.setId(id);
        toDoList.setStatus(status);
        toDoListService.update(toDoList);
        return R.ok();
    }

    @RequestMapping("/delete/{id}")
    public R delete(@LoginUser UserEntity user, @PathVariable String  id)  {
        //查询列表数据
        String username = user.getUsername();
        if(StringUtils.isBlank(username)){
            return R.error("用户不存在，请联系管理员");
        }

        toDoListService.delete(id);
        return R.ok();
    }

}
