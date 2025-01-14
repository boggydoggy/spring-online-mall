package com.example.onlinemall.prototype;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class PrototypeTest {
	@Test
	@DisplayName("Searching Prototype bean")
	public void prototypeBeanSerachingTest() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		System.out.println("prototypeBean1 = " + prototypeBean1);
		System.out.println("prototypeBean2 = " + prototypeBean2);
		Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
		
		prototypeBean1.addCount();
		System.out.println("prototypeBean1 count: " + prototypeBean1.getCount());
		Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);
		prototypeBean2.addCount();
		System.out.println("prototypeBean2 count: " + prototypeBean2.getCount());
		Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
		
	}
	
	@Scope("prototype")
	static class PrototypeBean {
		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init");
		}
		
		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy");
		}
		
		private int count = 0;
		
		public void addCount() {
			count++;
		}
		
		public int getCount() {
			return count;
		}
	}
}
