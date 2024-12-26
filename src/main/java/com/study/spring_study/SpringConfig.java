package com.study.spring_study;

import com.study.spring_study.repository.MemberRepository;
import com.study.spring_study.repository.MemoryMemberRepository;
import com.study.spring_study.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Bean 직접 등록하는 방식 (Component Scan 미사용)
 * 
 * 용도: 상황에 따라 구현 클래스 변경할 경우
 */
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() { // 메서드 param 값 보는 단축키: ctrl + p
        return new MemberService(memberRepository());
    }

    /**
     * DB 레파지토리가 변경될 경우, 구현 클래스 변경없이, return의 MemoryMemberRepository 이름만 변경하면 됨
     * @return
     */
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
