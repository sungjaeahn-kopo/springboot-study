package com.study.spring_study.controller;

import com.study.spring_study.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    /*
        중복으로 새로운 객체 생성할 필요없이,
        스프링에선 객체 등록만 하면 재사용 가능
        
        @Autowired 어느테이션으로 Spring container에 있는 MemberService를 가져옴

        또한, MemberService에서 @Autowired 인식할 수 있게 @Service 어노테이션을 붙여줘야함. (Component Scan 방식)
    */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}
