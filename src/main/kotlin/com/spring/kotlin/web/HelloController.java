package com.spring.kotlin.web;

import com.spring.kotlin.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";

    }
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        // requestParm -> 외부에서 API로 넘긴 파라미터 가져오는 어노테이션
        // 외부에서 name(@RequestParam("name")) 으로 넘긴 파라미터 -> name(String name) 으로 저장
        return new HelloResponseDto(name, amount);
    }
}
