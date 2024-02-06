package com.restaurant.controller.material;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.restaurant.client.ProductRecordClient;
import com.restaurant.context.BaseContext;
import com.restaurant.entity.Product;
import com.restaurant.entity.User;
import com.restaurant.result.Result;
import com.restaurant.service.material.ProductService;
import com.restaurant.vo.*;
import com.restaurant.vo.product.ProductAddVo;
import com.restaurant.vo.product.ProductQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.restaurant.constant.ProductConstant.PLANTING;

/**
 * 农户 农产品基本信息
 */
@Slf4j
@RestController
@RequestMapping("/dev-api/api/product")
public class ProductController {

    @Resource
    private ProductService productService;

    /**
     * 农作物展示
     *
     * @param productQueryVo
     * @return
     */
    @PostMapping("/page")
    public Result<Page> page(@RequestBody ProductQueryVo productQueryVo,HttpServletRequest request ) {

        String name = productQueryVo.getName();
        String type = productQueryVo.getType();
        Integer page = productQueryVo.getPage();
        Integer limit = productQueryVo.getLimit();

        Page<Product> pageInfo = new Page(page,limit);
        User user = (User)request.getSession().getAttribute("user");
        if (user.getId()==null){
            return Result.error("请先登录");
        }

        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Product::getUserId,user.getId());
        queryWrapper.like(StringUtils.isNotEmpty(name), Product::getName, name);
        queryWrapper.like(StringUtils.isNotEmpty(type), Product::getType, type);
        queryWrapper.orderByDesc(Product::getProductDate);

        productService.page(pageInfo, queryWrapper);

        return Result.success(pageInfo);

    }

    /**
     * 直接拿 list type
     *
     * @param
     * @return
     */

    @GetMapping("/type")
    public Result<List<Product>> type() {

       List<Product> result = productService.getType();

        return Result.success(result);
    }

    /**
     * 添加产品
     *
     * @param
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody ProductAddVo productAddVO,HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        if (user.getId()==null){
            return Result.error("请先登录");
        }

        Product product = new Product();
        BeanUtils.copyProperties(productAddVO,product);
        product.setUserId(user.getId());
        product.setUserTelephone(user.getPhone());
        product.setUserName(user.getName());

        //获取时间类型 vue提供组件多一个t不能直接类型转化
        // ISO 8601 格式的日期时间字符串
        String productDateString = productAddVO.getProductDate();
        // 定义日期时间格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        // 解析字符串为 ZonedDateTime（带时区信息的日期时间）
        ZonedDateTime productZonedDateTime = ZonedDateTime.parse(productDateString, formatter);
        // 转换为 LocalDateTime（不带时区信息的日期时间）
        LocalDateTime productLocalDateTime = productZonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
        // 设置Product对象的日期时间属性
        product.setProductDate(productLocalDateTime);

        //跟新作物状态为1 未运送
        product.setStatus(PLANTING);

        productService.addProduct(product);

        return Result.success();

    }
    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Product> get(@PathVariable Long id){
        Product product = productService.getById(id);

        return Result.success(product);
    }

    @PostMapping("/update")
    public Result update(@RequestBody ProductAddVo productAddVO) {

        Product product = new Product();
        BeanUtils.copyProperties(productAddVO, product);

        productService.updateById(product);

        return Result.success();

    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){

        productService.removeById(id);

        return Result.success();
    }


}
