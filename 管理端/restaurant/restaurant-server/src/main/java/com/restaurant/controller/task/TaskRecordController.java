package com.restaurant.controller.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.restaurant.client.TaskRecordContractClient;
import com.restaurant.entity.*;
import com.restaurant.result.Result;
import com.restaurant.service.StockService;
import com.restaurant.service.TaskRecordService;
import com.restaurant.service.TaskService;
import com.restaurant.vo.task.TaskRecordAddVo;
import com.restaurant.vo.task.TaskRecordListVo;
import com.restaurant.vo.transport.TransportStatus;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.restaurant.constant.UserConstant.FARMER;
import static com.restaurant.constant.UserConstant.RAW_MATERIAL_FACTORY;

@Slf4j
@RestController
@RequestMapping("/dev-api/api/taskRecord")
public class TaskRecordController {

    @Resource
    private TaskService taskService;

    @Resource
    private TaskRecordService taskRecordService;

    @Resource
    private StockService stockService;

    @Autowired
    private TaskRecordContractClient taskRecordContractClient;
    /**
     * 添加工作记录
     * @param taskRecordAddVo
     * @param request
     * @return
     */
    @PostMapping("/add")
    public Result<Void> addTaskRecord(@RequestBody TaskRecordAddVo taskRecordAddVo, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if (user.getId()==null){
            return Result.error("请先登录");
        }

        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setTaskId(taskRecordAddVo.getTaskId());
        taskRecord.setContent(taskRecordAddVo.getContent());
        taskRecord.setRemarks(taskRecordAddVo.getRemarks());

        taskRecord.setRecordTime(LocalDateTime.now());
        taskRecord.setUserId(user.getId());
        taskRecord.setCompanyId(user.getCompanyId());
        Task task = taskService.getById(taskRecordAddVo.getTaskId());
        task.setStatus(1);
        taskService.updateById(task);
        taskRecord.setTaskName(task.getTaskName());
        taskRecord.setProductId(taskRecord.getProductId());
        taskRecord.setProductId(task.getProductId());

        taskRecordContractClient.addTaskRecord(taskRecord);

        taskRecordService.save(taskRecord);

        return Result.success();
    }

    /**
     * 查看自己的工作记录
     * @param taskId
     * @param request
     * @return
     */
    @PostMapping("/list")
    public Result<List<TaskRecordListVo>> listRecord(@RequestParam Long taskId, HttpServletRequest request) throws ContractException {
//        LambdaQueryWrapper<TaskRecord> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(TaskRecord::getTaskId, taskId);
//
//        List<TaskRecord> list = taskRecordService.list(lambdaQueryWrapper);
//
//        User user = (User) request.getSession().getAttribute("user");
//        if (user.getId() == null) {
//            return Result.error("请先登录");
//        }
//
//        List<TaskRecordListVo> result = list.stream()
//                .map(taskRecord -> {
//                    TaskRecordListVo taskRecordList = new TaskRecordListVo();
//                    BeanUtils.copyProperties(taskRecord, taskRecordList);
//                    taskRecordList.setPhone(user.getPhone());
//                    taskRecordList.setName(user.getName());
//                    taskRecordList.setRecordTime(LocalDateTime.now());
//                    return taskRecordList;
//                })
//                .collect(Collectors.toList());
//
//
//        return Result.success(result);
        LambdaQueryWrapper<TaskRecord> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TaskRecord::getTaskId, taskId);

//        List<TaskRecord> list = taskRecordService.list(lambdaQueryWrapper);
        if (taskId==null){
            return Result.error("请重选");
        }
        List<TaskRecord> list = taskRecordContractClient.queryTaskRecordByTaskId(taskId);
        User user = (User) request.getSession().getAttribute("user");
        if (user.getId() == null) {
            return Result.error("请先登录");
        }

        List<TaskRecordListVo> result = list.stream()
                .map(taskRecord -> {
                    TaskRecordListVo taskRecordList = new TaskRecordListVo();
                    BeanUtils.copyProperties(taskRecord, taskRecordList);
                    taskRecordList.setPhone(user.getPhone());
                    taskRecordList.setName(user.getName());
                    taskRecordList.setRecordTime(LocalDateTime.now());
                    return taskRecordList;
                })
                .sorted(Comparator.comparing(TaskRecordListVo::getRecordTime).reversed())
                .collect(Collectors.toList());

        return Result.success(result);

    }


    /**
     * 查看原料的工作记录
     * @param taskId
     * @param request
     * @return
     */
    @PostMapping("/list2")
    public Result<List<TaskRecordListVo>> listRecord2(@RequestParam Long taskId, HttpServletRequest request) throws ContractException {

        LambdaQueryWrapper<TaskRecord> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TaskRecord::getTaskId, taskId);

//        List<TaskRecord> list = taskRecordService.list(lambdaQueryWrapper);
        if (taskId==null){
            return Result.error("请重选");
        }
        List<TaskRecord> list = taskRecordContractClient.queryTaskRecordByTaskId(taskId);

        User user = (User) request.getSession().getAttribute("user");
        if (user.getId() == null) {
            return Result.error("请先登录");
        }

        List<TaskRecordListVo> result = list.stream()
                .sorted(Comparator.comparing(TaskRecord::getRecordTime, Comparator.reverseOrder()))
                //        .sorted(Comparator.comparing(TaskRecord::getTimestamp))
                .map(taskRecord -> {
                    TaskRecordListVo taskRecordList = new TaskRecordListVo();
                    BeanUtils.copyProperties(taskRecord, taskRecordList);
                    taskRecordList.setPhone(user.getPhone());
                    taskRecordList.setName(user.getName());
                    return taskRecordList;
                })
                .collect(Collectors.toList());

        return Result.success(result);
    }

    /**
     * 跟新加工状态
     * @param taskId
     * @return
     */
    @PostMapping("/status")
    public Result<Void> changeStatus(@RequestParam Long taskId, HttpServletRequest request) {

        Task task = taskService.getById(taskId);
        Integer status = task.getStatus();
        task.setStatus(status == 0 ? 1 : (status == 1 ? 2 : status));
        taskService.updateById(task);

        Stock stock = stockService.getById(task.getProductId());
        stock.setStatus(2);
        stockService.updateById(stock);

        return Result.success();
    }

}
