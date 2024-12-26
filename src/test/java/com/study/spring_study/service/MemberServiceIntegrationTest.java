package com.study.spring_study.service;

import com.study.spring_study.domain.Member;
import com.study.spring_study.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 회원 관련 서비스 테스트 클래스
 * 테스트 클래스 실행 단축키
 * shift + F10
 *
 * 테스트 설계 기본 기법
 * 1) given
 * 2) when
 * 3) then
 *
 *
 * @AfterEach은 이제 @Transactional에 의해 불필요해짐.
 */

/**
 * DB Transaction
 * 테스트 환경에서는 DB commit 하지 않으면 테스트만 하고, 반영안하게 가능
 * >> @Transactional
 * 
 * 테스트를 반복 수행가능하게 함
 */

/**
 * @SpringBootTest
 * Spring Container와 함께 테스트 실행
 */

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

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

        // then
    }

}