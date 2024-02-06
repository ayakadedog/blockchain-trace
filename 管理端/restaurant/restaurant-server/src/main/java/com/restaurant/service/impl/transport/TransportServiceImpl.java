package com.restaurant.service.impl.transport;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restaurant.client.TransportRecordContractClient;
import com.restaurant.entity.*;
import com.restaurant.mapper.transport.TransportMapper;
import com.restaurant.service.transport.TransportRecordService;
import com.restaurant.service.transport.TransportService;
import com.restaurant.service.UserService;
import com.restaurant.service.material.MaterialService;
import com.restaurant.service.material.ProductService;
import com.restaurant.utils.getLocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static com.restaurant.constant.ProductConstant.PLANT_END;

@Slf4j
@Service
public class TransportServiceImpl extends ServiceImpl<TransportMapper, Transport> implements TransportService {

    @Resource
    private TransportRecordService transportRecordService;
    @Resource
    private ProductService productService;

    @Resource
    private TransportMapper transportMapper;

    @Resource
    private MaterialService materialService;

    @Autowired
    private TransportRecordContractClient transportRecordContractClient;

    @Override
    public void addNoticeTransport(Transport transport) {

        transport.setRecordTime(LocalDateTime.now());
        transport.setStatus(0);

        Product product = productService.getById(transport.getProductId());
        product.setStatus(PLANT_END);
        productService.updateById(product);
        transportMapper.insert(transport);


        TransportRecord transportRecord = new TransportRecord();
        BeanUtils.copyProperties(transport, transportRecord);
        transportRecord.setTransportId(transport.getId());


        String location = getLocation.resolveLocation(
                new Location(transport.getLng(), transport.getLat()));

        transportRecord.setLocationInfo(location);

        transportRecordService.save(transportRecord);
        transportRecordContractClient.addTransportRecordContractClient(transportRecord);

    }


    @Override
    public void noticeTransportCompany(Transport transport) {
        transport.setRecordTime(LocalDateTime.now());
        transport.setStatus(0);

        Material material = materialService.getById(transport.getProductId());
        material.setStatus(0);
        materialService.updateById(material);
        transportMapper.insert(transport);

        TransportRecord transportRecord = new TransportRecord();
        BeanUtils.copyProperties(transport, transportRecord);
        transportRecord.setTransportId(transport.getId());

        String location = getLocation.resolveLocation(
                new Location(transport.getLng(), transport.getLat()));

        transportRecord.setLocationInfo(location);

        transportRecordService.save(transportRecord);
        transportRecordContractClient.addTransportRecordContractClient(transportRecord);

    }

    @Override
    public Transport getByProductId(Long productId) {

        return transportMapper.getByProductId(productId);
    }

}
