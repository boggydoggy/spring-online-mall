package com.example.onlinemall.order;

import org.springframework.stereotype.Component;

import com.example.onlinemall.discount.DiscountMethod;
import com.example.onlinemall.discount.DiscountPolicy;
import com.example.onlinemall.discount.FixedDiscountPolicy;
import com.example.onlinemall.discount.RateDiscountPolicy;
import com.example.onlinemall.member.Member;
import com.example.onlinemall.member.MemberRepository;
import com.example.onlinemall.member.MemoryMemberRepository;

@Component
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
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
