package com.example.onlinemall.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LifeCycleConfig {
    @Bean
    public NetworkClient networkClient() {
		NetworkClient networkClient = new NetworkClient();
		networkClient.setUrl("https://www.github.com");
		return networkClient;
	}
}
