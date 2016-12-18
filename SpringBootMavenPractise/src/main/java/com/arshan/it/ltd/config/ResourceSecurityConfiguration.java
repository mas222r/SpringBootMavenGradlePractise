package com.arshan.it.ltd.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ResourceSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	private static final Logger log = LoggerFactory.getLogger(ResourceSecurityConfiguration.class);

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("-- the Resource security config details ---");
		super.configure(http);
		
		http.authorizeRequests()
		.antMatchers("/","/h2-console").permitAll()
		//.antMatchers("/retrieve/journal/all").hasRole("USER").anyRequest().authenticated()
		.anyRequest().authenticated()
		.and()
		//.httpBasic();
		.formLogin().loginPage("/login").permitAll()
		.and()
		.logout().permitAll();
		
	}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		log.info("-- the Resource security In memory authentication config details ---");
		
		authManagerBuilder.inMemoryAuthentication().withUser("user").password("default").roles("USER").and()
				.withUser("localpractise").password("capgemini123").roles("USER").and().withUser("admin")
				.password("admin123").roles("USER", "ADMIN");
	}
	
	

	
}
