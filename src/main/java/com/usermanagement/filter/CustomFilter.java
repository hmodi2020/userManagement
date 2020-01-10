package com.usermanagement.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Configuration
public class CustomFilter implements Filter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomFilter.class);
	
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		LOGGER.info("This is a  custom filter");
		chain.doFilter(request, response);
		
	}

	@Bean
	 public FilterRegistrationBean < CustomFilter> filterRegistrationBean() {
	  FilterRegistrationBean < CustomFilter > registrationBean = new FilterRegistrationBean();
	  registrationBean.setFilter(new CustomFilter());
	  registrationBean.addUrlPatterns("/users/");
	  return registrationBean;
	 }
}
