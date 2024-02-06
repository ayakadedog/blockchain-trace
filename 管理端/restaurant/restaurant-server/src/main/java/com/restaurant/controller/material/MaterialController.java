package com.restaurant.controller.material;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.restaurant.entity.Material;
import com.restaurant.entity.Product;
import com.restaurant.entity.User;
import com.restaurant.result.Result;
import com.restaurant.service.material.MaterialService;
import com.restaurant.vo.product.MaterialQueryVo;
import com.restaurant.vo.product.ProductQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.restaurant.constant.UserConstant.FARMER;
import static com.restaurant.constant.UserConstant.RAW_MATERIAL_FACTORY;

/**
 * 原料
 */
@Slf4j
@RestController
@RequestMapping("/dev-api/api/material")
public class MaterialController {

    @Resource
    private MaterialService materialService;

    /**
     * 农作物展示
     *
     * @param materialQueryVo
     * @return
     */
    @PostMapping("/page")
    public Result<Page> page(@RequestBody MaterialQueryVo materialQueryVo, HttpServletRequest request ) {

        String name = materialQueryVo.getName();
        String type = materialQueryVo.getType();
        Integer page = materialQueryVo.getPage();
        Integer limit = materialQueryVo.getLimit();

        Page<Material> pageInfo = new Page(page,limit);
        User user = (User)request.getSession().getAttribute("user");
        if (user.getId()==null){
            return Result.error("请先登录");
        }
        if(!user.getLevel().equals(RAW_MATERIAL_FACTORY)){
            return Result.error("请用原料厂账号登录");
        }

        //构造分页面
        LambdaQueryWrapper<Material> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Material::getCompanyId,user.getCompanyId());
        queryWrapper.like(StringUtils.isNotEmpty(name), Material::getName, name);
        queryWrapper.like(StringUtils.isNotEmpty(type), Material::getType, type);
        queryWrapper.orderByDesc(Material::getArrivalDate);

        materialService.page(pageInfo, queryWrapper);

        return Result.success(pageInfo);

    }
}
