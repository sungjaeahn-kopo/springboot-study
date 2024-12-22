package com.study.spring_study.service;

import com.study.spring_study.domain.Member;
import com.study.spring_study.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 회원 관련 서비스 테스트 클래스
 * 테스트 클래스 실행 단축키
 * shift + F10
 *
 * 테스트 설계 기본 기법
 * 1) given
 * 2) when
 * 3) then
 */
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        /**
         * Dependency Injection (의존성 주입)
         * Service에서 직접 객체를 생성하지 않고, 사용하는 클래스에서 의존성 주입하여 생성
         */
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get(); // 자동생성: ctrl + alt + v
        assertThat(member.getName()).isEqualTo(findMember.getName()); // static import: alt + enter
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        /* try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        // then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}