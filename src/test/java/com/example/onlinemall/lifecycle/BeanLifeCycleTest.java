package com.example.onlinemall.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanLifeCycleTest {
	@Test
	public void lifeCycleTest() {
		ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
		System.out.println("Generated new NetworkClient");
		NetworkClient networkClient = ac.getBean(NetworkClient.class);
		System.out.println("Close the ApplicationContext");
		ac.close(); // 스프링 컨테이너를 완전히 종료하는 기능, ConfigurationContext 타입 필요
	}
}
