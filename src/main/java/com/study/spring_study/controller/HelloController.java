package com.study.spring_study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 세가지방식
 * 1. 정적 컨텐츠
 * 파일을 그대로 내려주는 방식
 *
 * 2. MVC, 템플릿 엔진
 * 템플릿 엔진을 Model, View, Controller 방식으로 분할
 *
 * 3. API
 * HttpMessageConverter에 의해 객체 반환하는 방식
 */

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    /**
     * 
     * @param name
     * @return string
     * @ResponseBody http 통신에서 body 부분에 직접 return 내용을 주입
     */
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    /**
     * 
     * @param name
     * @return Hello Object
     * 
     * Hello 객체를 json 방식으로 return하는 함수
     * 이전과 달리, viewResolver가 처리하지 않고, HttpMessageConverter가 Json으로 변환
     * 문자처리: StringHttpMessageConverter
     * 객체처리: MappingJackson2HttpMessageConverter
     */
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }


    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
