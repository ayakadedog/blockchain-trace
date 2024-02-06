package com.restaurant.controller.material;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.restaurant.client.ProductRecordClient;
import com.restaurant.entity.User;
import com.restaurant.vo.product.ProductRecordAddVo;
import com.restaurant.entity.ProductRecord;
import com.restaurant.result.Result;
import com.restaurant.service.material.ProduceRecordService;
import com.restaurant.vo.product.ProductRecordQueryVo;
import com.restaurant.vo.product.ProductRecordVo;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
/**
 * 农户 农产品成长信息
 */
@Slf4j
@RestController
@RequestMapping("/dev-api/api/productRecord")
public class ProductRecordController {

    @Autowired
    private ProductRecordClient productRecordClient;
    @Resource
    private ProduceRecordService productRecordService;

    /**
     * 添加产品记录
     *
     * @param
     * @return
     */

    @PostMapping("/add")
    public Result<String> add(@RequestBody ProductRecordAddVo productRecordAddDto, HttpServletRequest request) {


        if (productRecordAddDto.getPicture() == null){
            return Result.error("请上传图片");
        }

        ProductRecord productRecord = new ProductRecord();

        // 使用BeanUtils复制属性值从ProductAddVO到Product
        BeanUtils.copyProperties(productRecordAddDto, productRecord);

        User user = (User)request.getSession().getAttribute("user");
        if (user.getId()==null)
            return Result.error("请先登录");

        productRecord.setUserId(user.getId());
        productRecord.setRecordTime(LocalDateTime.now());

        productRecordService.addProductRecord(productRecord);

        productRecordClient.addProductRecord(productRecord);

        return Result.success();

    }

    /**
     * 查询农产品溯源记录
     * @param productRecordQueryVo
     * @return
     */
    @PostMapping("page")
    public Result<List<ProductRecord>> query(@RequestBody ProductRecordQueryVo productRecordQueryVo) throws ContractException {

        Integer page = productRecordQueryVo.getPage();
        Integer pageSize = productRecordQueryVo.getLimit();

        Long productId = productRecordQueryVo.getProductId();

        List<ProductRecord> productRecords = productRecordClient.queryProductRecordByProductId(productId);

        return Result.success(productRecords);

//        Page<ProductRecord> pageInfo = new Page<>(page,pageSize);
//        Page<ProductRecordVo> result = new Page<>();
//
//        LambdaQueryWrapper<ProductRecord> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Objects.nonNull(productId),ProductRecord::getProductId, productId);
//
//
//
//        productRecordService.page(pageInfo, queryWrapper);
//
//        BeanUtils.copyProperties(pageInfo,result);
//
//        return Result.success(result);
    }

}
