package com.zben.shop.common.utils;

import java.util.UUID;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2019/11/26 0026 8:55
 */
public class TokenUtils {

    public static String getToken() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }
}
