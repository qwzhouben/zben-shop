package com.zben.shop.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zben.shop.common.constant.MQInterfaceType;
import com.zben.shop.common.constant.TableConstant;
import com.zben.shop.common.utils.DateUtils;
import com.zben.shop.member.entity.UserEntity;
import com.zben.shop.member.mapper.UserMapper;
import com.zben.shop.member.mq.producer.RegisterMailboxProducer;
import com.zben.shop.member.service.UserService;
import org.apache.activemq.command.ActiveMQQueue;
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

    @Value("${messages.queue}")
    private String EMAIL_QUEUE;

    @Override
    public void register(UserEntity userEntity) {
        if (userEntity != null) {
            userEntity.setCreated(DateUtils.getTimestamp());
            userEntity.setUpdated(DateUtils.getTimestamp());
        }
        userMapper.save(userEntity, TableConstant.TABLE_MB_USER);
        Destination destination = new ActiveMQQueue(EMAIL_QUEUE);
        //组装报文
        String mailMessage = mailMessage(userEntity.getEmail(), userEntity.getUsername());
        //mq
        registerMailboxProducer.send(destination, mailMessage);
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
