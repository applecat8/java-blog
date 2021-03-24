package com.applecat.blog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private JdbcTemplate jdbc;

	@Test
	void contextLoads() {
		final List<String> names = jdbc.query("select * from emp", new RowMapper<String>() {
			@Override
			public String mapRow(final ResultSet rs, final int rowNum) throws SQLException {
				return rs.getString("name");
			}
		});
		names.forEach(name -> System.out.println(name));
	}

}
