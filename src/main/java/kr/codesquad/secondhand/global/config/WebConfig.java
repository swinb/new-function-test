package kr.codesquad.secondhand.global.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.codesquad.secondhand.api.jwt.service.JwtService;
import kr.codesquad.secondhand.global.filter.AuthFilter;

@Configuration
public class WebConfig {
	@Bean
	public FilterRegistrationBean authFilter(JwtService provider, ObjectMapper mapper) {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new AuthFilter(mapper, provider));
		filterRegistrationBean.setOrder(1);
		return filterRegistrationBean;
	}
}
