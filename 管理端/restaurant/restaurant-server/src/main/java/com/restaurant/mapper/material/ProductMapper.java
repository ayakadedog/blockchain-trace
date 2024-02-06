package com.restaurant.mapper.material;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.entity.Product;
import com.restaurant.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    @Select("select * from product")
    List<Product> typeList();
}
