package com.restaurant.service.impl.material;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restaurant.entity.Product;
import com.restaurant.mapper.material.ProductMapper;
import com.restaurant.service.material.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {


    @Resource
    private ProductMapper productMapper;


    @Override
    public List<Product> getType() {
        List<Product> result = productMapper.typeList();
        return result;
    }

    @Override
    public void addProduct(Product product) {
        this.save(product);
    }


}
