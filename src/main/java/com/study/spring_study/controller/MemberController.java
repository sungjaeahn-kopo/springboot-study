package com.study.spring_study.controller;

import com.study.spring_study.domain.Member;
import com.study.spring_study.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
