package com.usermanagement.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.usermanagement.service.CustomUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	        auth.userDetailsService(userDetailsService)
//		  .passwordEncoder(getPasswordEncoder());
		 PasswordEncoder encoder = 
		          PasswordEncoderFactories.createDelegatingPasswordEncoder();
		        auth
		          .inMemoryAuthentication()
		          .withUser("user")
		          .password(encoder.encode("password"))
		          .roles("USER")
		          .and()
		          .withUser("admin")
		          .password(encoder.encode("admin"))
		          .roles("USER", "ADMIN");
	    }
	 
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	          .authorizeRequests()
	          .antMatchers("/").permitAll()
	          .anyRequest().fullyAuthenticated().and()
	          .csrf().disable()
	          .formLogin().disable()
	          .httpBasic();  
	    }
	    
	    private PasswordEncoder getPasswordEncoder() {
	        return new PasswordEncoder() {
	            @Override
	            public String encode(CharSequence charSequence) {
	                return charSequence.toString();
	            }

	            @Override
	            public boolean matches(CharSequence charSequence, String s) {
	                return true;
	            }
	        };
	    }
}
