package com.gongshw.aragog.master.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Author       : gongshw
 * Created At   : 16/1/31.
 */
@Repository
public class RuleDao {
	@Autowired
	private JdbcTemplate aragogJdbcTemplate;
}
