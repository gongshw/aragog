/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.common.configuration;

import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.activemq.ActiveMQSession
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.config.JmsListenerContainerFactory

/**
 * @author gongshiwei
 */
@Configuration
open class MessageQueueConnectionConfiguration {

    @Value("\${aragog.master}")
    private var isMaster = false;

    @Value("\${aragog.queue.broker.connect.url}")
    lateinit private var queueBrokerConnectUrl: String;

    @Value("\${aragog.queue.connection.concurrency:5}")
    lateinit private var queueConcurrency: String;

    @Bean
    fun aragogQueueConnectionFactory(): ActiveMQConnectionFactory {
        var queueBrokerConnectUrl: String;
        if (isMaster) {
            queueBrokerConnectUrl = "vm://" + AragogConstant.ARAGOG_BROKER_NAME;
        } else {
            queueBrokerConnectUrl = this.queueBrokerConnectUrl;
        }
        val factory = ActiveMQConnectionFactory(queueBrokerConnectUrl);
        factory.isTrustAllPackages = true;
        return factory;
    }

    @Bean
    fun aragogQueueContainerFactory(): JmsListenerContainerFactory<*> {
        val containerFactory = DefaultJmsListenerContainerFactory();
        containerFactory.setConnectionFactory(aragogQueueConnectionFactory());
        containerFactory.setConcurrency(queueConcurrency);
        containerFactory.setSessionAcknowledgeMode(ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
        return containerFactory;
    }
}
