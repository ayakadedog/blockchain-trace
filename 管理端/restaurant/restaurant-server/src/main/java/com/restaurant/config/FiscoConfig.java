package com.restaurant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author: zyt
 * @Description: 加载配置文件
 * @Date: Created in 10:04 2021/1/7
 */
@ImportResource(locations = "classpath:applicationContext.xml")
@Configuration
public class FiscoConfig {
}
