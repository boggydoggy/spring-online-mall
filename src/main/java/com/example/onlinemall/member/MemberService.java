package com.example.onlinemall.member;

/* 사용자가 사용하게 되는 method가 있는 class */
public interface MemberService {
    void join(Member member);
    Member findMember(Long id);
}
