package com.example.async.springbootdemorestasync.services.implementation;

import com.example.async.springbootdemorestasync.services.MessageProducerService;
import org.apache.activemq.artemis.api.core.ActiveMQException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@EnableJms
public class MessageProducerServiceImpl implements MessageProducerService {
    private JmsTemplate jmsTemplate;

    @Value("${artemis.queue-name}")
    private String queueName;

    @Autowired
    public MessageProducerServiceImpl(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendMessage(Object message) throws ActiveMQException {
        this.jmsTemplate.convertAndSend(queueName,message);
    }
}
