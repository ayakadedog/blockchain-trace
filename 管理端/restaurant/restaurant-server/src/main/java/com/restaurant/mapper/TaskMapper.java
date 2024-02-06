package com.restaurant.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.entity.Stock;
import com.restaurant.entity.Task;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {


}
