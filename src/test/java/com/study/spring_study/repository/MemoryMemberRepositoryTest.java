package com.study.spring_study.repository;

import com.study.spring_study.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * TDD (테스트 주도 개발)
 * 일반적으로, 로직 개발이후 테스트 메서드를 작성하지만,
 * 미리 만들 로직에 대해 테스트 틀을 짜고 그에 맞춰 로직 개발 진행하는 방식.
 */
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * AfterEach Annotation
     * '각 테스트 메서드 실행 이후' 메모리 비우기 위해 사용
     * 해당 메서드를 사용하지 않으면 테스트 메서드별 실행순서가 보장되지 않으므로 오류 발생 가능
     * 테스트 메서드별 의존성 있게 설계하면 안됨
     */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }


    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
