package com.arshan.it.ltd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

@Configuration
@EnableGlobalAuthentication
public class InMemorySecurityConfiguration {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.inMemoryAuthentication().withUser("user").password("default").roles("USER").and()
				.withUser("localpractise").password("capgemini123").roles("USER").and().withUser("admin")
				.password("admin123").roles("USER", "ADMIN");
	}
}
