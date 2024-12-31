package com.example.onlinemall;

import com.example.onlinemall.discount.DiscountMethod;
import com.example.onlinemall.discount.DiscountPolicy;
import com.example.onlinemall.discount.FixedDiscountPolicy;
import com.example.onlinemall.discount.RateDiscountPolicy;
import com.example.onlinemall.member.MemberRepository;
import com.example.onlinemall.member.MemberService;
import com.example.onlinemall.member.MemberServiceImpl;
import com.example.onlinemall.member.MemoryMemberRepository;
import com.example.onlinemall.order.OrderService;
import com.example.onlinemall.order.OrderServiceImpl;

public class AppConfig {
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy(DiscountMethod discountMethod) {
        if (discountMethod == DiscountMethod.FIXED) return new FixedDiscountPolicy();
        else return new RateDiscountPolicy();
    }

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService(DiscountMethod discountMethod) {
        return new OrderServiceImpl(memberRepository(), discountPolicy(discountMethod));
    }
}
