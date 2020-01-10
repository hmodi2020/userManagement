package com.usermanagement.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        PasswordEncoder encoder = 
	          PasswordEncoderFactories.createDelegatingPasswordEncoder();
	        auth
	          .inMemoryAuthentication()
	          .withUser("admin")
	          .password(encoder.encode("admin"))
	          .roles("USER", "ADMIN");
	    }
	 
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	          .authorizeRequests()
	          .antMatchers(HttpMethod.POST, "/users").hasAnyRole("USER","ADMIN")
	          .antMatchers(HttpMethod.PUT, "/users").hasAnyRole("USER","ADMIN")
	          .antMatchers(HttpMethod.PATCH, "/users").hasAnyRole("USER","ADMIN")
	          .antMatchers(HttpMethod.DELETE, "/users").hasAnyRole("USER","ADMIN")
	          .anyRequest().authenticated()
	          .and()
	          .csrf().disable()
	          .formLogin().and()
	          .httpBasic();  
	    }
}
