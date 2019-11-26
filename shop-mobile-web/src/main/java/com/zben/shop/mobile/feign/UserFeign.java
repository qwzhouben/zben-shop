package com.zben.shop.mobile.feign;

import com.zben.shop.member.api.UserApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2019/11/26 0026 11:06
 */
@FeignClient("member")
public interface UserFeign extends UserApi {
}
