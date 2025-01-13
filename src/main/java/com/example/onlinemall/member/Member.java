package com.example.onlinemall.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private long id;
    private String name;
    private Grade grade;

    public Member(long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
}
