package com.microLearnByDoing.department_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.microLearnByDoing.department_service.client.EmployeeClient;

@Configuration
public class WebClientConfig {

	@Autowired
	private LoadBalancedExchangeFilterFunction filter;
	
	@Bean
	public WebClient employeeWebConfig() {
		return WebClient
				.builder()
				.baseUrl("http://employee-service")
				.filter(filter)
				.build();
	}
	
	@Bean
	public EmployeeClient employeeClient() {
		HttpServiceProxyFactory proxy= HttpServiceProxyFactory
				.builder()
				.exchangeAdapter(WebClientAdapter.create(employeeWebConfig()))
				.build();
		
		return proxy.createClient(EmployeeClient.class);
	}
	
	
}
