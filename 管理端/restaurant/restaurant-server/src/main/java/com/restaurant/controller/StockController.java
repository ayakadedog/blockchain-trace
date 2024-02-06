package com.restaurant.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.restaurant.entity.*;
import com.restaurant.result.Result;
import com.restaurant.service.CompanyService;
import com.restaurant.service.StockService;
import com.restaurant.service.UserService;
import com.restaurant.vo.StockQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/dev-api/api/stock")
public class StockController {

    @Resource
    private StockService stockService;

    @Resource
    private UserService userService;

    @Resource
    private CompanyService companyService;

    /**
     * 加工厂原料展示
     * @param stockQueryVo
     * @param request
     * @return
     */
    @PostMapping("/page")
    public Result<Page> StockPage(@RequestBody StockQueryVo stockQueryVo, HttpServletRequest request) {

        //通过farmerid去判断谁是谁
//        Long id = stockQueryVo.getId();
        String name = stockQueryVo.getName();
        String type = stockQueryVo.getType();
        Integer page = stockQueryVo.getPage();
        Integer limit = stockQueryVo.getLimit();

        Page<Stock> pageInfo = new Page(page,limit);
        User user = (User)request.getSession().getAttribute("user");
        if (user.getId()==null){
            return Result.error("请先登录");
        }
        LambdaQueryWrapper<Stock> queryWrapper = new LambdaQueryWrapper();
//        queryWrapper.eq(Stock::getFarmerId,user.getId());
        queryWrapper.eq(Stock::getCompanyId,user.getCompanyId());
//        queryWrapper.ne(Stock::getStatus,2);
        queryWrapper.like(StringUtils.isNotEmpty(name), Stock::getName, name);
        queryWrapper.like(StringUtils.isNotEmpty(type), Stock::getType,type);
        queryWrapper.orderByDesc(Stock::getArrivalDate);

        stockService.page(pageInfo, queryWrapper);

        return Result.success(pageInfo);

    }

}
