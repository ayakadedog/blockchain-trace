package com.restaurant.mapper.transport;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.entity.TransportRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface TransportRecordMapper extends BaseMapper<TransportRecord> {

    @Select("select * from transport_record where transport_id = #{transportId}")
    List<TransportRecord> getRecordListByRecordId(Long transportId);

}
