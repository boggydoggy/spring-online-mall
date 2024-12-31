package com.example.onlinemall.order;

import com.example.onlinemall.member.Grade;
import com.example.onlinemall.member.Member;
import com.example.onlinemall.member.MemberService;
import com.example.onlinemall.member.MemberServiceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void orderTest() {
        Member vipMember = new Member(1L, "vip_member", Grade.VIP);
        memberService.join(vipMember);
        Order order = orderService.createOrder(1L, "USB", 24900);

        Assertions.assertThat(order.calculatePrice()).isEqualTo(23900);
    }
}
