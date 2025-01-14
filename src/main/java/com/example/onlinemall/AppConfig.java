package com.example.onlinemall;


import com.example.onlinemall.discount.DiscountPolicy;
import com.example.onlinemall.discount.FixedDiscountPolicy;
import com.example.onlinemall.member.MemberRepository;
import com.example.onlinemall.member.MemberService;
import com.example.onlinemall.member.MemberServiceImpl;
import com.example.onlinemall.member.MemoryMemberRepository;
import com.example.onlinemall.order.OrderService;
import com.example.onlinemall.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //스프링 빈과 관련된 설정을 알리는 annotation
public class AppConfig {
    @Bean  // 빈, 정의된 클래스
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixedDiscountPolicy();
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
