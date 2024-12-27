package com.study.spring_study.service;

import com.study.spring_study.domain.Member;
import com.study.spring_study.repository.MemberRepository;
import com.study.spring_study.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * MemberService 회원 관련 서비스
 * 회원 서비스 테스트 클래스 자동 생성 단축키
 * Ctrl + shift + t
 */

@Transactional
public class MemberService {

    // 테스트 케이스에서 해당 객체를 사용할 경우, 중복 구문이 발생함!
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    
    // 외부에서 respository를 넣어주도록 변경
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(
            m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            }
        );
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
