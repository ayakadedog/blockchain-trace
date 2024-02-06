package com.restaurant.mapper.material;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.entity.Product;
import com.restaurant.entity.ProductRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductRecordMapper extends BaseMapper<ProductRecord> {

}
