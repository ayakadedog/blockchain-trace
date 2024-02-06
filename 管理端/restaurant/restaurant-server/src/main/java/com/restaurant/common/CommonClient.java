package com.restaurant.common;

import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zyt
 * @Description: 此类提供一个发布合约的方法，并提供了基本实现方法，可以继承此类实现自己的方法
 * @Date: Created in 16:29 2021/1/20
 */
public abstract class CommonClient {

    // 日志记录器
    public static final Logger logger = LoggerFactory.getLogger(CommonClient.class.getName());

    // 用于存储合约名称和合约对象的映射关系
    private Map<String, Object> contractMap = new ConcurrentHashMap<>();

    // 构造方法
    public CommonClient() {
    }

    /**
     * 部署智能合约的方法
     *
     * @param contractName 合约名称
     * @param clazz        合约类
     * @param sdk          FISCO-BCOS SDK
     * @param <T>          合约类的泛型
     * @throws NoSuchMethodException     如果找不到指定的方法
     * @throws InvocationTargetException 如果目标方法抛出异常
     * @throws IllegalAccessException    如果无法访问指定的方法
     */
    @SuppressWarnings("unchecked")
    public <T> void deploy(String contractName, Class<T> clazz, BcosSDK sdk)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 为群组初始化client
        Client client = sdk.getClient();

        // 向群组部署合约
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        Method method = clazz.getMethod("deploy", Client.class, CryptoKeyPair.class);
        T result = (T) method.invoke(null, client, cryptoKeyPair);

        // 记录部署合约的日志信息
        logger.info("执行CommonClient的deploy方法");
        logger.info("部署合约 {} 成功:{}", contractName, result);

        // 将合约名称和合约对象的映射关系存储到contractMap中
        contractMap.put(contractName, result);
    }

    /**
     * 根据合约名称获取合约对象
     *
     * @param contractName 合约名称
     * @return 合约对象，如果不存在则返回null
     */
    public Object getContractMap(String contractName) {
        if (getContractMap().containsKey(contractName)) {
            return getContractMap().get(contractName);
        }
        return null;
    }

    /**
     * 获取整个contractMap
     *
     * @return contractMap
     */
    public Map<String, Object> getContractMap() {
        return contractMap;
    }

    /**
     * 设置整个contractMap
     *
     * @param contractMap 合约映射关系
     */
    public void setContractMap(Map<String, Object> contractMap) {
        this.contractMap = contractMap;
    }
}
