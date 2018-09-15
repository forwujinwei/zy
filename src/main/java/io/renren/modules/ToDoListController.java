package io.renren.modules;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.api.annotation.LoginUser;
import io.renren.modules.api.entity.UserEntity;
import io.renren.modules.resource.model.ResourcePersonalPoolModel;
import io.renren.modules.resource.model.ToDoList;
import io.renren.modules.resource.service.ToDoListService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        toDoList.setCreateDate(new Date());
        toDoList.setCreateBy(username);
        toDoList.setUpdateDate(new Date());
        toDoListService.save(toDoList);
        return R.ok();
    }

}
