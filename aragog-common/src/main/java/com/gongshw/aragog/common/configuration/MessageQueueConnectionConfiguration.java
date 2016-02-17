/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.common.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * @author gongshiwei
 */
@Configuration
public class MessageQueueConnectionConfiguration {

	@Value("${aragog.master}")
	private boolean isMaster;

	@Value("${aragog.queue.broker.connect.url}")
	private String queueBrokerConnectUrl;

	@Value("${aragog.queue.connection.concurrency:5}")
	private String queueConcurrency;

	@Bean
	public ConnectionFactory aragogQueueConnectionFactory() {
		String queueBrokerConnectUrl;
		if (isMaster) {
			queueBrokerConnectUrl = "vm://" + AragogConstant.ARAGOG_BROKER_NAME;
		} else {
			queueBrokerConnectUrl = this.queueBrokerConnectUrl;
		}
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(queueBrokerConnectUrl);
		factory.setTrustAllPackages(true);
		return factory;
	}

	@Bean
	public JmsListenerContainerFactory<?> aragogQueueContainerFactory() {
		DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
		containerFactory.setConnectionFactory(aragogQueueConnectionFactory());
		containerFactory.setConcurrency(queueConcurrency);
		containerFactory.setSessionAcknowledgeMode(ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
		return containerFactory;
	}
}
