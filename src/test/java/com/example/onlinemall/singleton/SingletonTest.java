package com.example.onlinemall.singleton;

import com.example.onlinemall.AppConfig;
import com.example.onlinemall.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void singletonTest() { //스프링 빈으로 불러온 클래스 정의는 싱글턴 패턴을 따름
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1=" + memberService1);
        System.out.println("memberService2=" + memberService2);

        Assertions.assertThat(memberService1).isEqualTo(memberService2);
    }
}
