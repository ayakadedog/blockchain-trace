package com.restaurant.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.entity.Good;
import com.restaurant.entity.Stock;
import com.restaurant.entity.Task;
import com.restaurant.entity.User;
import com.restaurant.result.Result;
import com.restaurant.service.GoodService;
import com.restaurant.service.StockService;
import com.restaurant.service.TaskRecordService;
import com.restaurant.service.TaskService;
import com.restaurant.vo.GoodAddVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/dev-api/api/good")
public class GoodController {

    @Resource
    private GoodService goodService;
    @Resource
    private TaskService taskService;
    @Resource
    private StockService stockService;

    /**
     * 跟新加工状态 + 商品入库
     * @param goodAddVo
     * @param request
     * @return
     */
    @PostMapping("/status")
    public Result<Void> changeStatus(@RequestBody GoodAddVo goodAddVo, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        if (user.getId()==null){
            return Result.error("请先登录");
        }
        //完工
        Task task = taskService.getById(goodAddVo.getTaskId());
        Integer status = task.getStatus();
        task.setStatus(status == 0 ? 1 : (status == 1 ? 2 : status));
        taskService.updateById(task);
        //完记
        Stock stock = stockService.getById(task.getProductId());
        stock.setStatus(2);
        stockService.updateById(stock);
        //加good
        Good good = new Good();
        BeanUtils.copyProperties(goodAddVo,good);
        good.setUserId(user.getId());
        good.setUserName(user.getName());
        good.setPhone(user.getPhone());
        good.setProductId(task.getProductId());
        good.setStatus(0);
        goodService.save(good);
        return Result.success();
    }
}
