package com.example.onlinemall.autoscan;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.onlinemall.AutoAppConfig;
import com.example.onlinemall.discount.DiscountPolicy;
import com.example.onlinemall.discount.SubDiscountPolicy;
import com.example.onlinemall.member.Grade;
import com.example.onlinemall.member.Member;
import com.example.onlinemall.member.MemberRepository;
import com.example.onlinemall.member.MemberService;
import com.example.onlinemall.order.Order;
import com.example.onlinemall.order.OrderService;

import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoScanTest {
	ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
	
	@Test
	@DisplayName("Print All Beans")
	void findAllBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			Object bean = ac.getBean(beanDefinitionName);
			System.out.println("names = " + beanDefinitionName + ", object = " + bean);
		}
	}
	
	@Test
	@DisplayName("Check Discount value of discount policies")
	void getPrimaryDiscountPolicy() {
		Member member =  new Member(1L, "tester", Grade.VIP);
		
		ac.getBean(MemberService.class).join(member);
		
		Order primaryServiceOrder = ac.getBean(OrderService1.class).createOrder(1L, "item1", 20000);
		Order qualifierServiceOrder = ac.getBean(OrderService2.class).createOrder(1L,"item2", 20000);
		
		System.out.println("primaryServiceOrder = " + primaryServiceOrder);
		System.out.println("qualifierServiceOrder = " + qualifierServiceOrder);
		
		Assertions.assertThat(primaryServiceOrder.getDiscountPrice()).isEqualTo(2000);
		Assertions.assertThat(qualifierServiceOrder.getDiscountPrice()).isEqualTo(1000);
	}
	
	@Test
	@DisplayName("Choose one of several Discount Policies")
	void getAllDiscountPolicy() {
		Member testMember = new Member(1L, "tester", Grade.VIP);
		ac.getBean(MemberService.class).join(testMember);
		
		Order fixServiceOrder = ac.getBean(OrderService3.class).createOrder(1L, "item3", 20000, "fixedDiscountPolicy");
		Order rateServciOrder = ac.getBean(OrderService3.class).createOrder(1L, "item3", 20000, "rateDiscountPolicy");
		
		System.out.println("fixServiceOrder = " + fixServiceOrder);
		System.out.println("rateServiceOrder = " + rateServciOrder);
		
		Assertions.assertThat(fixServiceOrder.getDiscountPrice()).isEqualTo(1000);
		Assertions.assertThat(rateServciOrder.getDiscountPrice()).isEqualTo(2000);
	}

	@Component
	static class OrderService1 implements OrderService {
		private MemberRepository memberRepository;
		private DiscountPolicy discountPolicy;
		
		@Autowired
		public OrderService1(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
			this.memberRepository = memberRepository;
			this.discountPolicy = discountPolicy;
		}

		@Override
		public Order createOrder(Long memberId, String itemName, int itemPrice) {
			Member member = memberRepository.findById(memberId);
			int discountPrice = discountPolicy.discount(member, itemPrice);
			return new Order(memberId, itemName, itemPrice, discountPrice);
		}
		
	}
	
	@Component
	static class OrderService2 implements OrderService {
		private MemberRepository memberRepository;
		private DiscountPolicy discountPolicy;
		
		//@Autowired (optional)
		public OrderService2(MemberRepository memberRepository, @SubDiscountPolicy DiscountPolicy discountPolicy) {
			this.memberRepository = memberRepository;
			this.discountPolicy = discountPolicy;
		}

		@Override
		public Order createOrder(Long memberId, String itemName, int itemPrice) {
			Member member = memberRepository.findById(memberId);
			int discountPrice = discountPolicy.discount(member, itemPrice);
			return new Order(memberId, itemName, itemPrice, discountPrice);
		}
		
	}
	
	@Component
	@RequiredArgsConstructor
	static class OrderService3{
		private final MemberRepository memberRepository;
		private final Map<String, DiscountPolicy> discountPolicyMap;
		
		public Order createOrder(Long memberId, String itemName, int itemPrice, String policyName) {
			Member member = memberRepository.findById(memberId);
			DiscountPolicy discountPolicy = discountPolicyMap.get(policyName);
			int discountPrice = discountPolicy.discount(member, itemPrice);
			return new Order(memberId, itemName, itemPrice, discountPrice);
		}
	}
}
