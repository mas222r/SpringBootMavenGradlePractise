package com.arshan.it.ltd.config;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableGlobalAuthentication
public class CustomJdbcSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
	
	@Bean
	public UserDetailsService userDetailsService(JdbcTemplate jdbcTemplate)
	{
		RowMapper<User> userRowMapper = (ResultSet rs,int i) ->
		new User(
				rs.getString("ACCOUNT_NAME"),
				rs.getString("PASSWORD"),
				rs.getBoolean("ENABLED"),
				rs.getBoolean("ENABLED"),
				rs.getBoolean("ENABLED"),
				rs.getBoolean("ENABLED"),
				AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN"));
		
		return userName -> jdbcTemplate.queryForObject("SELECT * from ACCOUNT where ACCOUNT_NAME = ?", userRowMapper, userName);
		
	}
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		super.init(auth);
		auth.userDetailsService(this.userDetailsService);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}
	
	

}
