package com.smartbank.common.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository

public class SequenceGeneratorRepository {
	 @Autowired
	    private JdbcTemplate jdbcTemplate;

	    public Long getNextSequence(String sequenceName) {

	        String sql = "SELECT nextval('" + sequenceName + "')";

	        return jdbcTemplate.queryForObject(sql, Long.class);
	    }
}
