package com.example.async.springbootdemorestasync.config;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.converter.SimpleMessageConverter;

@Configuration
public class ArtemisConfig {

    @Value("${artemis.broker-url}")
    private String brokerUrl;

    @Value("${artemis.username}")
    private String userName;

    @Value("${artemis.password}")
    private String password;

    @Bean
    public ActiveMQConnectionFactory senderActiveMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory =  new ActiveMQConnectionFactory(brokerUrl);

        activeMQConnectionFactory
                .setUser(userName)
                .setPassword(password);

        return activeMQConnectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();

        cachingConnectionFactory.setTargetConnectionFactory(senderActiveMQConnectionFactory());
        cachingConnectionFactory.setSessionCacheSize(10);
        cachingConnectionFactory.setReconnectOnException(true);
        return cachingConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(cachingConnectionFactory());
    }

    @Bean
    public MessageConverter converter(){
        return new SimpleMessageConverter();
    }

}
