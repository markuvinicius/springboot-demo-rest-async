package com.example.async.springbootdemorestasync.services;

import org.apache.activemq.artemis.api.core.ActiveMQException;

public interface MessageProducerService {
    public void sendMessage(Object message) throws ActiveMQException;
}
