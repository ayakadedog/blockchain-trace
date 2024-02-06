package com.restaurant.service.impl.material;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restaurant.entity.ProductRecord;
import com.restaurant.mapper.material.ProductRecordMapper;
import com.restaurant.service.material.ProduceRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Slf4j
@Service
public class ProductRecordServiceImpl extends ServiceImpl<ProductRecordMapper, ProductRecord> implements ProduceRecordService {


    @Resource
    private ProductRecordMapper productRecordMapper;

    public void addProductRecord(ProductRecord productRecord) {

        this.save(productRecord);
    }
}
