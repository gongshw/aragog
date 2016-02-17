/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.gongshw.aragog.common.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * @author gongshiwei
 */
@Configuration
public class DatabaseConfiguration {

	@Autowired
	private DataSource aragogDataSource;

	@Bean
	DataSource aragogDataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScripts("db/init_h2.sql", "db/init_mysql.sql")
				.build();
	}

	@Bean
	JdbcTemplate aragogJdbcTemplate() {
		return new JdbcTemplate(aragogDataSource);
	}

	@Bean
	NamedParameterJdbcTemplate aragogNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(aragogDataSource);
	}
}
