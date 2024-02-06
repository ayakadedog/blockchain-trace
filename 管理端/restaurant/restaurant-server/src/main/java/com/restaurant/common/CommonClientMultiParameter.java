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
 * @Classname CommonClientMultiParameter
 * @Description 多参数公共客户端，可以对多参数数据进行上链，只需继承此类，并根据需要重写方法即可
 * @Date 2021/4/24 21:36
 * @Created by zyt
 */
public abstract class CommonClientMultiParameter {
    public static final Logger logger = LoggerFactory.getLogger(CommonClientMultiParameter.class.getName());

    public CommonClientMultiParameter() {
    }

    private Map<String, Object> contractMap = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <T> void deploy(String contractName, Class<T> clazz, BcosSDK sdk) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 为群组1初始化client
        Client client = sdk.getClient();
        // 向群组1部署合约
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        Method method = clazz.getMethod("deploy", Client.class, CryptoKeyPair.class);
        T result = (T) method.invoke(null, client, cryptoKeyPair);
        logger.info("CommonClientMultiParameter");
        logger.info("部署合约 {} 成功:{}" , contractName, result);
        contractMap.put(contractName, result);
    }

    public Object getContractMap(String contractName) {
        if (getContractMap().containsKey(contractName)) {
            return getContractMap().get(contractName);
        }
        return null;
    }

    public Map<String, Object> getContractMap() {
        return contractMap;
    }

    public void setContractMap(Map<String, Object> contractMap) {
        this.contractMap = contractMap;
    }

}
