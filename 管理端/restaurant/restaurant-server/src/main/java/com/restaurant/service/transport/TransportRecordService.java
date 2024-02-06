package com.restaurant.service.transport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.restaurant.entity.Transport;
import com.restaurant.entity.TransportRecord;
import com.restaurant.entity.User;
import com.restaurant.vo.transport.TransportVo;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

public interface TransportRecordService extends IService<TransportRecord> {

    TransportVo getRoute(Transport transport, User user, Long productId) throws ContractException;

    TransportVo getRoute2(Long transportId) throws ContractException;

    void add(Transport transport);

}


