package com.example.onlinemall.member;

/* DB가 사용하는 method가 있는 class */
public interface MemberRepository {
    void save(Member member);
    Member findById(Long id);
}
