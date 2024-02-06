package com.restaurant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restaurant.entity.TaskRecord;
import com.restaurant.mapper.TaskRecordMapper;
import com.restaurant.service.TaskRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class TaskRecordRecordServiceImpl extends ServiceImpl<TaskRecordMapper,TaskRecord> implements TaskRecordService {


    @Resource
    private TaskRecordMapper taskRecordMapper;

    
}
