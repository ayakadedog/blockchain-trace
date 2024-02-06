package com.restaurant.service.material;

import com.baomidou.mybatisplus.extension.service.IService;
import com.restaurant.entity.ProductRecord;

public interface ProduceRecordService extends IService<ProductRecord> {

    void addProductRecord(ProductRecord productRecord);


}
