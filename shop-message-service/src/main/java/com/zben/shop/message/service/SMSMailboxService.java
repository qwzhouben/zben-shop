package com.zben.shop.message.service;

import com.alibaba.fastjson.JSONObject;
import com.zben.shop.message.MessageAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @DESC: 发送邮件
 * @author: zhouben
 * @date: 2019/11/25 0025 16:24
 */
@Service
@Slf4j
public class SMSMailboxService implements MessageAdapter {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void distribute(JSONObject jsonObject) {
        //接收mq， 发送邮件
        String email = jsonObject.getString("mail");
        String username = jsonObject.getString("username");
        log.info("### mail:{}, username:{}", email, username);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(email);
        message.setSubject("会员注册成功");
        message.setText(username + ", 恭喜您注册成功！");
        log.info("###发送短信邮箱 mail:{}", email);
        mailSender.send(message);
    }
}
