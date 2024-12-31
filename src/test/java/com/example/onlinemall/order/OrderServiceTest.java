package com.example.onlinemall.order;

import com.example.onlinemall.AppConfig;
import com.example.onlinemall.discount.DiscountMethod;
import com.example.onlinemall.member.Grade;
import com.example.onlinemall.member.Member;
import com.example.onlinemall.member.MemberService;
import com.example.onlinemall.member.MemberServiceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    OrderService orderService;

    @Test
    void fixedOrderTest() {
        Member vipMember = new Member(1L, "vip_member", Grade.VIP);
        memberService.join(vipMember);
        orderService = appConfig.orderService(DiscountMethod.FIXED);
        Order order = orderService.createOrder(1L, "USB", 25000);

        Assertions.assertThat(order.calculatePrice()).isEqualTo(24000);
    }

    @Test
    void rateOrderTest() {
        Member vipMember = new Member(1L, "vip_member", Grade.VIP);
        memberService.join(vipMember);
        orderService = appConfig.orderService(DiscountMethod.RATE);
        Order order = orderService.createOrder(1L, "USB", 25000);

        Assertions.assertThat(order.calculatePrice()).isEqualTo(22500);
    }
}
