package com.zben.shop.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @DESC: 包装返回类
 * @author: zhouben
 * @date: 2019/11/22 0022 13:37
 */
@Data
public class ResultResponse<T> {

    private Integer code;

    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private ResultResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        if (data != null) {
            this.data = data;
        }
    }

    private ResultResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultResponse setSuccess(Integer code, String msg, Object data) {
        return new ResultResponse<>(code, msg, data);
    }

    public static ResultResponse setSuccess(Object data) {
        return setSuccess(Constant.HTTP_200, Constant.SUCCESS, data);
    }

    public static ResultResponse setSuccess(Integer code, String  msg) {
        return setSuccess(code, msg, null);
    }

    public static ResultResponse setSuccess() {
        return new ResultResponse(Constant.HTTP_200, Constant.SUCCESS);
    }

    public static ResultResponse setFail(Integer code, String msg) {
        return new ResultResponse(code, msg);
    }

    public static ResultResponse setFail() {
        return setFail(Constant.HTTP_500, Constant.ERROR);
    }

    public static ResultResponse setFail(String msg) {
        return setFail(Constant.HTTP_500, msg);
    }

}
