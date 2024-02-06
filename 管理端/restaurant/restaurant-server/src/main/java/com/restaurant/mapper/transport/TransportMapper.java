package com.restaurant.mapper.transport;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.entity.Transport;
import com.restaurant.entity.TransportRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface TransportMapper extends BaseMapper<Transport> {


    @Select("SELECT * FROM transport WHERE product_id = #{productId}")
    Transport getByProductId(Long productId);

}
