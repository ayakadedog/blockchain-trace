package com.restaurant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.restaurant.entity.Task;

public interface TaskService extends IService<Task> {


    void addNoticeTask(Task task);
}
