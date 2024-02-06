package com.restaurant.client;


import com.restaurant.common.CommonClient;
import com.restaurant.contract.ProductRecord;
import com.restaurant.contract.TaskRecordContract;
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
public class ProductRecordClient extends CommonClient implements ApplicationRunner {
    public final String configFile = ProductRecordClient.class.getClassLoader().getResource("config-example.toml").getPath();



    public static final Logger logger = LoggerFactory.getLogger(ProductRecordClient.class.getName());

    public List<com.restaurant.entity.ProductRecord> queryProductRecordByProductId(Long ProductId) throws ContractException {

        logger.info("queryProductRecordByProductId 调用查询方法");
        String contractAddress = "0x6849f21d1e455e9f0712b1e99fa4fcd23758e8f1";
        // 创建一个私钥对
        BcosSDK sdk =  BcosSDK.build(configFile);
        // 为群组group初始化client
        Client client = sdk.getClient("group0");
        // 获取密钥对
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        ProductRecord productRecord = ProductRecord.load(contractAddress, client, cryptoKeyPair);

        List<ProductRecord.Record> recordsByProductId = productRecord.getRecordsByProductId(BigInteger.valueOf(ProductId));

        if (recordsByProductId.isEmpty()) {
            return null;
        }

        List<com.restaurant.entity.ProductRecord> list = new ArrayList<>();


        logger.info("调用CRUDClient的query方法");
        logger.info("结果：{}", recordsByProductId);

        for (int i = 0; i < recordsByProductId.size(); i++) {

            com.restaurant.entity.ProductRecord record = new com.restaurant.entity.ProductRecord();
            record.setProductId(recordsByProductId.get(i).getProductId().longValue());
            record.setUserId(recordsByProductId.get(i).getUserId().longValue());
            record.setTemperature(recordsByProductId.get(i).getTemperature());
            record.setIllumination(recordsByProductId.get(i).getIllumination());
            record.setPicture(recordsByProductId.get(i).getPictureIpfsHash());
            record.setWater(recordsByProductId.get(i).getWater());
            record.setGrowthSituation(recordsByProductId.get(i).getGrowthSituation());

            // 假设时间格式为"yyyy-MM-dd HH:mm:ss.SSSSSSSSS"
            String timeString = recordsByProductId.get(i).getRecordTime();
            LocalDateTime recordTime = LocalDateTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            // 然后设置给record对象的recordTime属性
            record.setRecordTime(recordTime);


            record.setRemark(recordsByProductId.get(i).getRemark());
            list.add(record);

        }
        return list;

    }

    public void addProductRecord(com.restaurant.entity.ProductRecord entity) {

        logger.info("addProductRecord 调用添加记录方法");

        BigInteger productId = BigInteger.valueOf(entity.getProductId());
        BigInteger userId = BigInteger.valueOf(entity.getUserId());
        String temperature = entity.getTemperature();
        String illumination = entity.getIllumination();
        String remark = entity.getRemark();
        String pictureIpfsHash = entity.getPicture();
        String water = entity.getWater();
        String growthSituation = entity.getGrowthSituation();
        String recordTime = entity.getRecordTime().toString();
        String contractAddress = "0x6849f21d1e455e9f0712b1e99fa4fcd23758e8f1";
        // 创建一个私钥对
        BcosSDK sdk =  BcosSDK.build(configFile);
        // 为群组group初始化client
        Client client = sdk.getClient("group0");
        // 获取密钥对
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        ProductRecord productRecord = ProductRecord.load(contractAddress, client, cryptoKeyPair);

//        ProductRecord productRecord = (ProductRecord) getContractMap().get("ProductRecord");
        // 调用 addRecord 方法
        TransactionReceipt receipt = productRecord.addRecord(productId, userId, temperature, illumination, remark, pictureIpfsHash, water, growthSituation, recordTime);

        logger.info("调用CRUDClient的insert方法");
        logger.info("结果：{}", receipt);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BcosSDK sdk = SpringUtils.getBean("bcosSDK");
        deploy("ProductRecord", ProductRecord.class, sdk);
    }
}
