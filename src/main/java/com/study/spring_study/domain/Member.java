package com.study.spring_study.domain;

import jakarta.persistence.*;

/**
 * ORM (Object Relation Mapping)
 * 객체와 관계형 DB를 매핑하는 기술
 * @Entity로 jpa에서 관리하는 객체임을 선언
 */
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
