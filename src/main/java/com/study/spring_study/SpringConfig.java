package com.study.spring_study;

import com.study.spring_study.repository.*;
import com.study.spring_study.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Spring Bean 직접 등록하는 방식 (Component Scan 미사용)
 * 
 * 용도: 상황에 따라 구현 클래스 변경할 경우
 */
@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() { // 메서드 param 값 보는 단축키: ctrl + p
        return new MemberService(memberRepository());
    }

    /**
     * 개방-폐쇄의 원칙 (OCP, Open-Closed Principle)
     * Spring DI를 이용해 기존 코드를 수정하지 않고, 설정만으로 구현 클래스를 변경
     *
     * DB 레파지토리가 변경될 경우, 구현 클래스 변경없이, return의 MemoryMemberRepository 이름만 변경하면 됨
     * MemoryMemberRepository는 DB가 정해지기 이전의 작업물이고, 이후 DB가 정해지고 JdbcMemberRepository로 변경
     *
     * @return
     */
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
