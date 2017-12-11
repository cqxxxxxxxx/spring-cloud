package com.cqx.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
@RestController
public class ProviderApplication {

	@Autowired
	DiscoveryClient discoveryClient;
	@Value("${server.port}")
	String port;

	@GetMapping("/provider/info")
	public String dc() {
		String services = "Services: " + discoveryClient.getServices() + "\nPort:" + port;
		System.out.println(services);
		return services;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProviderApplication.class, args);
	}
}
