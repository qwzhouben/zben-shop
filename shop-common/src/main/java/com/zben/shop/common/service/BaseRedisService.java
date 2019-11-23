package com.zben.shop.common.service;

import com.zben.shop.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2019/11/22 0022 14:16
 */
@Component
public class BaseRedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void set(String key, Object value, Long timeOut) {
        if (value != null) {
            if (timeOut == null) {
                timeOut = Constant.TIMEOUT;
            }
            if (value instanceof String) {
                String strValue = (String) value;
                stringRedisTemplate.opsForValue().set(key, strValue, timeOut, TimeUnit.SECONDS);
            }
        }
    }

    public void set(String key, Object value) {
        set(key, value, null);
    }

    public Object getKey(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void delKey(String key) {
        stringRedisTemplate.delete(key);
    }

}
