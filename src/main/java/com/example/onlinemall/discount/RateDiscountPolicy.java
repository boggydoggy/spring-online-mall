package com.example.onlinemall.discount;

import com.example.onlinemall.member.Grade;
import com.example.onlinemall.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{
    private static final int PERCENT_VALUE = 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * PERCENT_VALUE / 100;
        } else return price;
    }
}
