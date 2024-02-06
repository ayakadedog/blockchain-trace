package com.restaurant.client;


import com.restaurant.common.CommonClient;
import com.restaurant.contract.ProductRecord;
import com.restaurant.contract.TransportRecordContract;
import com.restaurant.entity.TransportRecord;
import com.restaurant.utils.SpringUtils;
import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname CRUDClient
 * @Description sdk客户端，可以不使用ApplicationRunner，这里是让spring容器启动部署合约
 * @Date 2021/3/25 21:45
 * @Created by zyt
 */
@Service
public class TransportRecordContractClient extends CommonClient implements ApplicationRunner {

    public static final Logger logger = LoggerFactory.getLogger(TransportRecordContractClient.class.getName());

    public final String configFile = TransportRecordContractClient.class.getClassLoader().getResource("config-example.toml").getPath();


    public List<TransportRecord> queryTransportRecordContractByTransportId(Long transportId) throws ContractException {

        logger.info("queryTransportRecordContractClientByProductId 调用查询方法");
        String contractAddress = "0xc8ead4b26b2c6ac14c9fd90d9684c9bc2cc40085";
        // 创建一个私钥对
        BcosSDK sdk =  BcosSDK.build(configFile);
        // 为群组group初始化client
        Client client = sdk.getClient("group0");
        // 获取密钥对
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        TransportRecordContract transportRecord = TransportRecordContract.load(contractAddress, client, cryptoKeyPair);

//        TransportRecordContract transportRecord = (TransportRecordContract) getContractMap().get("TransportRecordContract");
        List<TransportRecordContract.Record> recordsByTransportId = transportRecord.getRecordsByTransportId(BigInteger.valueOf(transportId));

        if (recordsByTransportId.isEmpty()) {
            return null;
        }

        List<TransportRecord> list = new ArrayList<>();


        logger.info("调用CRUDClient的query方法");
        logger.info("结果：{}", recordsByTransportId);

        for (int i = 0; i < recordsByTransportId.size(); i++) {

            TransportRecord record = new com.restaurant.entity.TransportRecord();
            record.setProductId(recordsByTransportId.get(i).getProductId().longValue());
            record.setUserId(recordsByTransportId.get(i).getUserId().longValue());
            record.setTransportId(transportId);
            record.setLat(recordsByTransportId.get(i).getLat());
            record.setLng(recordsByTransportId.get(i).getLng());
            record.setLocationInfo(recordsByTransportId.get(i).getLocationInfo());
            // 假设时间格式为"yyyy-MM-dd HH:mm:ss.SSSSSSSSS"
            String timeString = recordsByTransportId.get(i).getRecordTime();
            LocalDateTime recordTime = LocalDateTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            // 然后设置给record对象的recordTime属性
            record.setRecordTime(recordTime);
            list.add(record);

        }
        return list;

    }

    public void addTransportRecordContractClient(TransportRecord transportRecord) {

        logger.info("addTransportRecordContractClient 调用添加记录方法");

        BigInteger productId = BigInteger.valueOf(transportRecord.getProductId());
        BigInteger userId = BigInteger.valueOf(transportRecord.getUserId());
        BigInteger transportId = BigInteger.valueOf(transportRecord.getTransportId());
        String lat = transportRecord.getLat();
        String lng = transportRecord.getLng();
        String locationInfo = transportRecord.getLocationInfo();

        String recordTime = transportRecord.getRecordTime().toString();

//        TransportRecordContract record = (TransportRecordContract) getContractMap().get("TransportRecordContract");
        String contractAddress = "0xc8ead4b26b2c6ac14c9fd90d9684c9bc2cc40085";
        // 创建一个私钥对
        BcosSDK sdk =  BcosSDK.build(configFile);
        // 为群组group初始化client
        Client client = sdk.getClient("group0");
        // 获取密钥对
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        // 调用 addRecord 方法
        TransportRecordContract record = TransportRecordContract.load(contractAddress, client, cryptoKeyPair);

        TransactionReceipt receipt = record.addRecord(productId, userId, transportId, recordTime, lng, lat, locationInfo);

        logger.info("调用CRUDClient的insert方法");
        logger.info("结果：{}", receipt);

    }

    public List<TransportRecord> queryTransportRecordContractByProductId(Long productId) throws ContractException {

        logger.info("queryTransportRecordContractClientByProductId 调用查询方法");

        String contractAddress = "0xc8ead4b26b2c6ac14c9fd90d9684c9bc2cc40085";
        // 创建一个私钥对
        BcosSDK sdk =  BcosSDK.build(configFile);
        // 为群组group初始化client
        Client client = sdk.getClient("group0");
        // 获取密钥对
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        TransportRecordContract transportRecord = TransportRecordContract.load(contractAddress, client, cryptoKeyPair);
//        TransportRecordContract transportRecord = (TransportRecordContract) getContractMap().get("TransportRecordContract");

        List<TransportRecordContract.Record> recordsByProductId = transportRecord.getRecordsByProductId(BigInteger.valueOf(productId));

        if (recordsByProductId.isEmpty()) {
            return null;
        }

        List<TransportRecord> list = new ArrayList<>();


        logger.info("调用CRUDClient的query方法");
        logger.info("结果：{}", recordsByProductId);

        for (int i = 0; i < recordsByProductId.size(); i++) {

            TransportRecord record = new com.restaurant.entity.TransportRecord();
            record.setProductId(recordsByProductId.get(i).getProductId().longValue());
            record.setUserId(recordsByProductId.get(i).getUserId().longValue());
            record.setTransportId(productId);
            record.setLat(recordsByProductId.get(i).getLat());
            record.setLng(recordsByProductId.get(i).getLng());
            record.setLocationInfo(recordsByProductId.get(i).getLocationInfo());
            // 假设时间格式为"yyyy-MM-dd HH:mm:ss.SSSSSSSSS"
            String timeString = recordsByProductId.get(i).getRecordTime();
            LocalDateTime recordTime = LocalDateTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            // 然后设置给record对象的recordTime属性
            record.setRecordTime(recordTime);
            list.add(record);

        }
        return list;

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BcosSDK sdk = SpringUtils.getBean("bcosSDK");
        deploy("TransportRecordContract", TransportRecordContract.class, sdk);
    }
}
