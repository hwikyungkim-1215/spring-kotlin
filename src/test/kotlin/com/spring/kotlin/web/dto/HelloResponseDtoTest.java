package com.spring.kotlin.web.dto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat; // 검증 메소드

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name); // 같을때만 성공
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}