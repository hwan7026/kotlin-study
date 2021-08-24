package com.example.kotlinstudy.member.service

import com.example.kotlinstudy.common.exception.DataNotFountException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberServiceTest{

    @InjectMocks
    lateinit var memberService: MemberService

    @Test
    fun test(){
        assertThatThrownBy {
            memberService.findById(1L)
        }.isInstanceOf(DataNotFountException::class.java)
            .hasMessageContaining("Data Not Found")
    }
}