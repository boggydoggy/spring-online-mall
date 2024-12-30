package com.example.onlinemall.order;

import com.example.onlinemall.discount.DiscountPolicy;
import com.example.onlinemall.discount.FixedDiscountPolicy;
import com.example.onlinemall.member.Member;
import com.example.onlinemall.member.MemberRepository;
import com.example.onlinemall.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixedDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
