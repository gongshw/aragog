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
open class DatabaseConfiguration {

    @Autowired
    lateinit private var aragogDataSource: DataSource;

    @Bean
    open fun aragogDataSource() =
            EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .addScripts("db/init_h2.sql", "db/init_mysql.sql")
                    .build();

    @Bean
    open fun aragogJdbcTemplate() = JdbcTemplate(aragogDataSource);

    @Bean
    open fun aragogNamedParameterJdbcTemplate() = NamedParameterJdbcTemplate(aragogDataSource);
}
