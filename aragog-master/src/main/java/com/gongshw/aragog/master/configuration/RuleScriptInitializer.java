package com.gongshw.aragog.master.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Author       : gongshw
 * Created At   : 16/1/31.
 */
@Component
@ConditionalOnProperty("aragog.master")
public class RuleScriptInitializer {

	@Autowired
	JdbcTemplate aragogJdbcTemplate;

	@PostConstruct
	public void init() {

	}
}
