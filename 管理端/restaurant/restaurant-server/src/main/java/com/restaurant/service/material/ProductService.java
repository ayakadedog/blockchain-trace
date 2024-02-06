package com.restaurant.service.material;

import com.baomidou.mybatisplus.extension.service.IService;
import com.restaurant.entity.Product;

import java.util.List;

public interface ProductService extends IService<Product> {


    List<Product> getType();

    void addProduct(Product product);
}
