package com.restaurant.client;


import com.google.gson.Gson;
import com.restaurant.common.CommonClient;
import com.restaurant.contract.TaskRecordContract;
import com.restaurant.entity.TaskRecord;
import com.restaurant.utils.SpringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.client.protocol.response.BlockNumber;
import org.fisco.bcos.sdk.v3.codec.EventEncoder;
import org.fisco.bcos.sdk.v3.codec.FunctionEncoderInterface;
import org.fisco.bcos.sdk.v3.codec.FunctionReturnDecoderInterface;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.manager.TransactionProcessor;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Classname CRUDClient
 * @Description sdk客户端，可以不使用ApplicationRunner，这里是让spring容器启动部署合约
 * @Date 2021/3/25 21:45
 * @Created by zyt
 */
@Service
public class TaskRecordContractClient extends CommonClient implements ApplicationRunner {

    public final String configFile = TaskRecordContractClient.class.getClassLoader().getResource("config-example.toml").getPath();

    public static Logger logger = LoggerFactory.getLogger(TaskRecordContractClient.class.getName());

    public List<TaskRecord> queryTaskRecordByTaskId(Long taskId) throws ContractException {

        logger.info("queryTaskRecordContractClientByProductId 调用查询方法");
        String contractAddress = "0x4721d1a77e0e76851d460073e64ea06d9c104194";
        // 创建一个私钥对
        BcosSDK sdk =  BcosSDK.build(configFile);
        // 为群组group初始化client
        Client client = sdk.getClient("group0");
        // 获取密钥对
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        // 加载合约
        TaskRecordContract taskRecord = TaskRecordContract.load(contractAddress, client, cryptoKeyPair);
//        // 打印交易回执的交易哈希
//        System.out.println(receipt.getTransactionHash());

        // 这段合约每次启动就部署数据 数据丢失
//        TaskRecordContract productRecord = (TaskRecordContract) getContractMap().get("TaskRecordContract");

        List<TaskRecordContract.Record> recordsByTaskId = taskRecord.getRecordsByTaskId(BigInteger.valueOf(taskId));

        if (recordsByTaskId.isEmpty()) {
            return null;
        }

        List<TaskRecord> list = new ArrayList<>();


        logger.info("调用CRUDClient的query方法");
        logger.info("结果：{}", recordsByTaskId);

        for (int i = 0; i < recordsByTaskId.size(); i++) {

            TaskRecord record = new TaskRecord();
            record.setProductId(recordsByTaskId.get(i).getProductId().longValue());
            record.setUserId(recordsByTaskId.get(i).getUserId().longValue());
            record.setTaskId(taskId);
            record.setCompanyId(recordsByTaskId.get(i).getCompanyId().longValue());

            record.setTaskName(recordsByTaskId.get(i).getTaskName());
            record.setContent(recordsByTaskId.get(i).getContent());
            record.setRemarks(recordsByTaskId.get(i).getRemarks());
            // 假设时间格式为"yyyy-MM-dd HH:mm:ss.SSSSSSSSS"
            String timeString = recordsByTaskId.get(i).getRecordTime();
            LocalDateTime recordTime = LocalDateTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            // 然后设置给record对象的recordTime属性
            record.setRecordTime(recordTime);
            list.add(record);

        }
        return list;

    }

    public void addTaskRecord(TaskRecord taskRecord) {

        logger.info("addTaskRecordContractClient 调用添加记录方法");

        BigInteger productId = BigInteger.valueOf(taskRecord.getProductId());
        BigInteger userId = BigInteger.valueOf(taskRecord.getUserId());
        BigInteger taskId = BigInteger.valueOf(taskRecord.getTaskId());
        BigInteger companyId = BigInteger.valueOf(taskRecord.getCompanyId());

        String taskName = taskRecord.getTaskName();
        String content = taskRecord.getContent();
        String remarks = taskRecord.getRemarks();
        String recordTime = taskRecord.getRecordTime().toString();
        String contractAddress = "0x4721d1a77e0e76851d460073e64ea06d9c104194";
        // 创建一个私钥对
        BcosSDK sdk =  BcosSDK.build(configFile);
        // 为群组group初始化client
        Client client = sdk.getClient("group0");
        // 获取密钥对
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        TaskRecordContract record = TaskRecordContract.load(contractAddress, client, cryptoKeyPair);

//        TaskRecordContract record = (TaskRecordContract) getContractMap().get("TaskRecordContract");
        // 调用 addRecord 方法
        TransactionReceipt receipt = record.addRecord(taskId, productId, userId, companyId, recordTime, remarks, taskName, content);

        logger.info("调用CRUDClient的insert方法");
        logger.info("结果：{}", receipt);

    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        BcosSDK sdk = SpringUtils.getBean("bcosSDK");
        deploy("TaskRecordContract", TaskRecordContract.class, sdk);
    }
}
