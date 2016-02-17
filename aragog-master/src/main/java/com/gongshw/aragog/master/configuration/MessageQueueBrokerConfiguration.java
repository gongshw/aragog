/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.master.configuration;

import com.gongshw.aragog.common.configuration.AragogConstant;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gongshiwei
 */
@Configuration
@ConditionalOnProperty("aragog.master")
public class MessageQueueBrokerConfiguration {

	@Value("${aragog.master}")
	private boolean isMaster;

	@Value("${aragog.queue.broker.config.url}")
	private String queueBrokerConfigUrl;

	@Bean
	@ConditionalOnProperty("aragog.master")
	public BrokerService aragogBroker() throws Exception {
		BrokerService brokerService = BrokerFactory.createBroker(queueBrokerConfigUrl);
		brokerService.setBrokerName(AragogConstant.ARAGOG_BROKER_NAME);
		brokerService.setPersistent(true);
		brokerService.start();
		return brokerService;
	}
}
