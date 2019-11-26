package com.zben.shop.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zben.shop.common.Constant;
import com.zben.shop.common.ResultResponse;
import com.zben.shop.common.constant.MQInterfaceType;
import com.zben.shop.common.constant.TableConstant;
import com.zben.shop.common.service.BaseRedisService;
import com.zben.shop.common.utils.DateUtils;
import com.zben.shop.common.utils.MD5Util;
import com.zben.shop.common.utils.TokenUtils;
import com.zben.shop.member.entity.UserEntity;
import com.zben.shop.member.mapper.UserMapper;
import com.zben.shop.member.mq.producer.RegisterMailboxProducer;
import com.zben.shop.member.service.UserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2019/11/22 0022 20:23
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RegisterMailboxProducer registerMailboxProducer;
    @Autowired
    private BaseRedisService baseRedisService;

    @Value("${messages.queue}")
    private String EMAIL_QUEUE;

    @Override
    public void register(UserEntity userEntity) {
        if (userEntity == null) {
            return;
        }
        userEntity.setCreated(DateUtils.getTimestamp());
        userEntity.setUpdated(DateUtils.getTimestamp());
        userEntity.setPassword(md5PassSalt(userEntity.getPhone(), userEntity.getPassword()));
        userMapper.save(userEntity, TableConstant.TABLE_MB_USER);
        Destination destination = new ActiveMQQueue(EMAIL_QUEUE);
        //组装报文
        String mailMessage = mailMessage(userEntity.getEmail(), userEntity.getUsername());
        //mq
        registerMailboxProducer.send(destination, mailMessage);
    }

    private String md5PassSalt(String phone, String password) {
        return MD5Util.MD5(phone + password);
    }

    @Override
    public ResultResponse login(UserEntity userEntity) {
        if (userEntity == null) {
            return ResultResponse.setFail();
        }

        //从数据库查询
        UserEntity userPhoneAndPwd = userMapper.findByPhoneAndPwd(userEntity.getPhone(), md5PassSalt(userEntity.getPhone(), userEntity.getPassword()));
        //生成token，作为key存到redis，值为userId
        if (userPhoneAndPwd == null) {
            return ResultResponse.setFail("账号或密码错误");
        }
        String token = TokenUtils.getToken();
        baseRedisService.set(token, userPhoneAndPwd.getId() + "", Constant.TIMEOUT);
        return ResultResponse.setSuccess(token);
    }

    @Override
    public ResultResponse getUser(String token) {
        if (StringUtils.isEmpty(token)) {
            return ResultResponse.setFail("token不能为空");
        }
        String userId = (String) baseRedisService.getKey(token);
        if (userId == null) {
            return ResultResponse.setFail("用户过期，重新登陆");
        }
        UserEntity userEntity = userMapper.findById(userId);
        if (userEntity != null) {
            userEntity.setPassword(null);
        }
        return ResultResponse.setSuccess(userEntity);
    }

    private String mailMessage(String email, String username) {
        JSONObject root = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("interfaceType", MQInterfaceType.SMS_MAIL);
        JSONObject content = new JSONObject();
        content.put("mail", email);
        content.put("username", username);
        root.put("header", header);
        root.put("content", content);
        return root.toJSONString();
    }


}
