package com.restaurant.service.transport;

import com.baomidou.mybatisplus.extension.service.IService;
import com.restaurant.entity.Transport;
import com.restaurant.entity.TransportRecord;

public interface TransportService extends IService<Transport> {

    void addNoticeTransport(Transport transport);

    Transport getByProductId(Long productId);

    void noticeTransportCompany(Transport transport);
}
