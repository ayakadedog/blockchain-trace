package com.restaurant.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.restaurant.entity.Company;
import com.restaurant.entity.User;
import com.restaurant.result.Result;
import com.restaurant.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.restaurant.constant.CompanyConstant.PROCESSING_FACTORY;

@Slf4j
@RestController
@RequestMapping("/dev-api/api/company")
public class CompanyController {

    @Resource
    private CompanyService companyService;

    @PostMapping("/list")
    public Result<List<Company>> page(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getId() == null) {
            return Result.error("请先登录");
        }
        List<Company> list = companyService.list(
                new LambdaQueryWrapper<Company>()
                        .eq(Company::getType, PROCESSING_FACTORY)
                        .ne(Company::getId, user.getCompanyId())
        );
        return Result.success(list);

    }


}
