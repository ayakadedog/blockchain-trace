package com.restaurant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restaurant.entity.Good;
import com.restaurant.entity.TaskRecord;
import com.restaurant.mapper.GoodMapper;
import com.restaurant.mapper.TaskRecordMapper;
import com.restaurant.service.GoodService;
import com.restaurant.service.TaskRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService {


    @Resource
    private GoodMapper goodMapper;


}
