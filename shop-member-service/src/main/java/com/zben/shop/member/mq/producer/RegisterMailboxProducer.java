
package com.zben.shop.member.mq.producer;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * mq消息发送
 */
@Service("registerMailboxProducer")
public class RegisterMailboxProducer {
	@Autowired
    private JmsMessagingTemplate jmsMessagingTemplate ; 
	
	public void send(Destination destination,String json){
		jmsMessagingTemplate.convertAndSend(destination, json);
	}
}
