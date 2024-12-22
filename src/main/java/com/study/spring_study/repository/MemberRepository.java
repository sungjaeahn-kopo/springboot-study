package com.study.spring_study.repository;

import com.study.spring_study.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    /**
     * Optinal: null 처리하는 방법
     */
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
