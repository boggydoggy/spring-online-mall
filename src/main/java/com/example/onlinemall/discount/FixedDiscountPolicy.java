package com.example.onlinemall.discount;

import com.example.onlinemall.member.Grade;
import com.example.onlinemall.member.Member;

public class FixedDiscountPolicy implements DiscountPolicy {
    private int discountFixAmount = 1000; //할인할 고정 금액

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
