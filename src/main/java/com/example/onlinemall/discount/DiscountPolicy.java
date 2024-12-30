package com.example.onlinemall.discount;

import com.example.onlinemall.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
