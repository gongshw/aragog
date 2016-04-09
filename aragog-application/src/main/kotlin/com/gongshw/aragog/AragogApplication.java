/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author gongshiwei
 */
@SpringBootApplication
@EnableAsync
@EnableJms
public class AragogApplication {

	private final Logger logger = LoggerFactory.getLogger(AragogApplication.class);

	@Value("${aragog.master}")
	private boolean isMaster;

	@Value("${aragog.discover.enable}")
	private boolean discoverWorkerEnabled;

	@Autowired
	Environment env;

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = new SpringApplicationBuilder()
				.bannerMode(Banner.Mode.OFF).sources(AragogApplication.class).run(args);
		AragogApplication aragogApplication = context.getBean(AragogApplication.class);
		aragogApplication.checkProperties();
	}

	public void checkProperties() {
		if (isMaster) {
			logger.info("run as master");
		}
		if (discoverWorkerEnabled) {
			logger.info("discover worker enabled");
		}
		if (!isMaster && !discoverWorkerEnabled) {
			logger.error("aragog run as a non-master point and no worker enabled");
			System.exit(1);
		}
	}
}
