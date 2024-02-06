package com.restaurant.controller.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.restaurant.entity.Task;
import com.restaurant.entity.User;
import com.restaurant.result.Result;
import com.restaurant.service.GoodService;
import com.restaurant.service.TaskService;
import com.restaurant.vo.task.TaskAddVo;
import com.restaurant.vo.task.TaskQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/dev-api/api/task")
public class TaskController {

    @Resource
    private TaskService taskService;


    /**
     * 通知工作
     *
     * @param
     * @return
     */
    @PostMapping("/notice")
    public Result noticeTask(@RequestBody TaskAddVo taskAddVo, HttpServletRequest request) {
        Task task = new Task();
        BeanUtils.copyProperties(taskAddVo, task);
        User user = (User) request.getSession().getAttribute("user");
        task.setCompanyId(user.getCompanyId());
        taskService.addNoticeTask(task);
        return Result.success();
    }


    @PostMapping("/page")
    public Result<Page> TaskPage(@RequestBody TaskQueryVo taskQueryVo, HttpServletRequest request) {
        String name = taskQueryVo.getName();
        Integer status = taskQueryVo.getStatus();
        Integer page = taskQueryVo.getPage();
        Integer limit = taskQueryVo.getLimit();
        Long id = taskQueryVo.getId();

        Page<Task> pageInfo = new Page<>(page, limit);
        User user = (User)request.getSession().getAttribute("user");
        if (user.getId()==null){
            return Result.error("请先登录");
        }
        if (user.getLevel()!=2){
            return Result.error("账号不匹配");
        }
        LambdaQueryWrapper<Task> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Task::getCompanyId,user.getCompanyId());
        queryWrapper.like(StringUtils.isNotEmpty(name), Task::getTaskName, name);
        queryWrapper.like(status != null,Task::getStatus,status);
        queryWrapper.like(id != null,Task::getId,id);
        queryWrapper.orderByDesc(Task::getRecordTime);

        taskService.page(pageInfo, queryWrapper);

        return Result.success(pageInfo);
    }

}
