package com.example.onlinemall.member;

import com.example.onlinemall.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberServiceTest {
//    AppConfig appConfig = new AppConfig();
//    MemberService memberService = appConfig.memberService();
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); // 스프링 컨테이너: ApplicationContext와 같이 빈들을 저장하고 있는 저장소
    MemberService memberService = ac.getBean("memberService", MemberService.class); //첫번째 인자는 빈 정의 시 사용한 method 이름, 두번째 인자는 빈으로 정의된 클래스

    @Test
    void signUpTest() {
        Member member = new Member(1L, "tester_1", Grade.BASIC);
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
