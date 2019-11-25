package com.zben.shop.message.mq.distribute;

import com.alibaba.fastjson.JSONObject;
import com.zben.shop.common.constant.MQInterfaceType;
import com.zben.shop.message.MessageAdapter;
import com.zben.shop.message.service.SMSMailboxService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @DESC: 消费消息 mq 从队列获取最新消息
 * @author: zhouben
 * @date: 2019/11/25 0025 16:13
 */
@Component
@Slf4j
public class CustomerDistribute {

    @Autowired
    private SMSMailboxService smsMailboxService;

    @JmsListener(destination = "mail_queue")
    public void distribute(String json) {
        log.info("###消息服务###收到消息,消息内容 json:{}", json);
        if (StringUtils.isEmpty(json)) {
            log.info("###消息服务###收到消息,消息内容 json: 为空");
            return;
        }
        JSONObject jsonObject = new JSONObject().parseObject(json);
        JSONObject header = jsonObject.getJSONObject("header");
        String interfaceType = header.getString("interfaceType");
        MessageAdapter messageAdapter = null;
        switch (interfaceType) {
            case MQInterfaceType.SMS_MAIL :
            //发送邮件
                messageAdapter = smsMailboxService;
                break;
            default:
                break;
        }
        JSONObject content = jsonObject.getJSONObject("content");
        messageAdapter.distribute(content);
    }
}
