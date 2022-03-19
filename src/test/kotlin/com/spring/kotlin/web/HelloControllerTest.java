package com.spring.kotlin.web;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //springRunner 실행시킴
@WebMvcTest // web에 집중, @Controller, @ControllerAdivce 등 사용가능
public class HelloControllerTest {

    @Autowired // 빈 주입
    private MockMvc mvc; // API 테스트

    @Test
    public void hello가_리턴된다() throws Exception{

        String hello = "hello";

        mvc.perform(get("/hello")) // mvc를 통해 get 요청
                .andExpect(status().isOk()) // mvc.perfor 결과 검증
                .andExpect(content().string(hello)); // controller에서 리턴하는 값과 비교(맞는지 검증)
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform( // param -> API 테스트시 사용될 요청 파라미터 설정, String 값만 허용(숫자 데이터는 문자열로 변경해야함)
                        get("/hello/dto")
                                .param("name", name)
                                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                // jsonPath -> json 응답값을 필드별로 검증, name -> $.name 으로 검증
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

    }
}
