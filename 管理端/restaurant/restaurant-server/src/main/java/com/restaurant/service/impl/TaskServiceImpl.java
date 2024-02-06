package com.restaurant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restaurant.entity.Stock;
import com.restaurant.entity.Task;
import com.restaurant.mapper.TaskMapper;
import com.restaurant.service.StockService;
import com.restaurant.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.time.LocalDateTime;

import static com.restaurant.constant.UserConstant.IN_WORK;

@Slf4j
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {


    @Resource
    private TaskMapper taskMapper;
    @Resource
    private StockService stockService;

    @Override
    public void addNoticeTask(Task task) {
        Stock stock = stockService.getById(task.getProductId());
        stock.setStatus(1);
        stockService.updateById(stock);
        //要跟新stock status
        task.setRecordTime(LocalDateTime.now());
        task.setStatus(0);
        this.save(task);
    }
}
