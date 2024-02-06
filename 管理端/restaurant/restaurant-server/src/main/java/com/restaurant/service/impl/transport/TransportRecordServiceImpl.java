package com.restaurant.service.impl.transport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restaurant.client.TransportRecordContractClient;
import com.restaurant.entity.*;
import com.restaurant.mapper.transport.TransportRecordMapper;
import com.restaurant.service.CompanyService;
import com.restaurant.service.transport.TransportRecordService;
import com.restaurant.service.transport.TransportService;
import com.restaurant.service.UserService;
import com.restaurant.utils.getLocation;
import com.restaurant.vo.transport.TransportVo;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.restaurant.constant.UserConstant.FARMER;
import static com.restaurant.constant.UserConstant.RAW_MATERIAL_FACTORY;

@Slf4j
@Service
public class TransportRecordServiceImpl extends ServiceImpl<TransportRecordMapper, TransportRecord> implements TransportRecordService {

    @Resource
    private TransportService transportService;

    @Resource
    private UserService userService;

    @Resource
    private TransportRecordMapper transportRecordMapper;

    @Resource
    private CompanyService companyService;

    @Autowired
    private TransportRecordContractClient transportRecordContractClient;

    /**
     * 农户 商家 获取路线
     * @param transport
     * @param user
     * @param productId
     * @return
     */
    @Override
    public TransportVo getRoute(Transport transport,User user,Long productId) throws ContractException {

        Company company = companyService.getById(user.getCompanyId());

        TransportVo transportVo = new TransportVo();
        transportVo.setStartCoord(new Location(transport.getLng(),transport.getLat()));
        transportVo.setEndCoord(new Location(company.getLng(),company.getLat()));

        LambdaQueryWrapper<TransportRecord> transportRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
        transportRecordLambdaQueryWrapper.eq(TransportRecord::getProductId,productId);

        List<TransportRecord> transportRecordList = transportRecordContractClient.queryTransportRecordContractByProductId(productId);

        if (!transportRecordList.isEmpty()) {
            List<Location> locations = new ArrayList<>();
            for (int i=0 ;i<transportRecordList.size();i++){
                locations.add(new Location(transportRecordList.get(i).getLng(),transportRecordList.get(i).getLat()));
            }
            transportVo.setPath(locations);
        }
        transportVo.setCenter(transportVo.getStartCoord());
        return transportVo;
//        List<TransportRecord> transportRecordList = transportRecordMapper.selectList(transportRecordLambdaQueryWrapper);
//
//        if (!transportRecordList.isEmpty()) {
//            List<Location> locations = new ArrayList<>();
//            for (int i=0 ;i<transportRecordList.size();i++){
//                locations.add(new Location(transportRecordList.get(i).getLng(),transportRecordList.get(i).getLat()));
//            }
//            transportVo.setPath(locations);
//        }
//        transportVo.setCenter(transportVo.getStartCoord());
//        return transportVo;
    }

    /**
     * 快递员获取路线
     * @param transportId
     * @return
     */

    @Override
    public TransportVo getRoute2(Long transportId) throws ContractException {

        Transport transport = transportService.getById(transportId);
        User user = userService.getById(transport.getUserId());
        if (Objects.equals(user.getLevel(), RAW_MATERIAL_FACTORY)){
            Company company = companyService.getById(user.getCompanyId());

            TransportVo transportVo = new TransportVo();
            transportVo.setStartCoord(new Location(company.getLng(),company.getLat()));
            transportVo.setEndCoord(new Location(company.getLng(),company.getLat()));

            List<TransportRecord> recordList = transportRecordContractClient.queryTransportRecordContractByTransportId(transportId);
            List<Location> locations = recordList.stream()
                    .map(record -> new Location(record.getLng(), record.getLat()))
                    .collect(Collectors.toList());


            transportVo.setPath(locations);
            return transportVo;
//            List<TransportRecord> recordList = transportRecordMapper.getRecordListByRecordId(transportId);
//            List<Location> locations = recordList.stream()
//                    .map(record -> new Location(record.getLng(), record.getLat()))
//                    .collect(Collectors.toList());
//
//
//            transportVo.setPath(locations);
//            return transportVo;
        }
        if (user.getLevel().equals(FARMER)) {
            Company company = companyService.getById(user.getCompanyId());

            TransportVo transportVo = new TransportVo();
            transportVo.setStartCoord(new Location(transport.getLng(),transport.getLat()));
            transportVo.setEndCoord(new Location(company.getLng(),company.getLat()));

            List<TransportRecord> recordList = transportRecordContractClient.queryTransportRecordContractByTransportId(transportId);

            List<Location> locations = recordList.stream()
                    .map(record -> new Location(record.getLng(), record.getLat()))
                    .collect(Collectors.toList());


            transportVo.setPath(locations);
            return transportVo;

//            List<Location> locations = recordList.stream()
//                    .map(record -> new Location(record.getLng(), record.getLat()))
//                    .collect(Collectors.toList());
//
//
//            transportVo.setPath(locations);
//            return transportVo;
        }

        return null;

    }

    @Override
    public void add(Transport transport) {
        TransportRecord transportRecord = new TransportRecord();
        BeanUtils.copyProperties(transport,transportRecord);
        transportRecord.setId(null);
        transportRecord.setTransportId(transport.getId());
        transportRecord.setRecordTime(LocalDateTime.now());

        String location = getLocation.resolveLocation(
                new Location(transport.getLng(), transport.getLat()));

        transportRecord.setLocationInfo(location);

        this.save(transportRecord);
        transportRecordContractClient.addTransportRecordContractClient(transportRecord);


    }


}
