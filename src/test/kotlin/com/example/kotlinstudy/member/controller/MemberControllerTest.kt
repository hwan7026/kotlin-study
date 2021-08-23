package com.example.kotlinstudy.member.controller

import ReqMemberUpdateDTO
import com.example.kotlinstudy.member.domain.dto.ReqMemberSaveDTO
import com.example.kotlinstudy.member.domain.entity.Member
import com.example.kotlinstudy.member.domain.enums.GenderType
import com.example.kotlinstudy.member.repository.MemberRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter


@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class MemberControllerTest (
    val memberRepository: MemberRepository,
    val webApplicationContext : WebApplicationContext
){

    @Autowired
    lateinit var memberController : MemberController

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    val url = "/api/v1/member"

    @BeforeEach
    fun beforeSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilters<DefaultMockMvcBuilder>(
            CharacterEncodingFilter("UTF-8", true))
            .build()

        memberRepository.save(
            Member(
                1L,
                "test@email.com",
                "이승환",
                GenderType.MAN,
                "1992-02-27",
                "010-4331-7026"
            )
        )
    }

    @DisplayName("회원 정보 확인 테스트")
    @Test
    fun getMemberTest() {
        mockMvc.perform(
            get("$url/1")
        )
        .andExpect(status().isOk)
        .andDo(print())
    }

    @DisplayName("회원 정보 저장 테스트")
    @Test
    fun saveMember() {
        val request = ReqMemberSaveDTO(
            "test@email.com",
            "password",
            "테스트",
            GenderType.MAN,
            "1992-02-27",
            "010-4331-7026"
        )

        this.mockMvc
            .perform(
                post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(request)))
            .andExpect(status().isOk)
            .andDo(print())
    }

    @Test
    fun updateMember() {
        val request = ReqMemberUpdateDTO(
            "update@email.com",
            "이승환",
            GenderType.MAN,
            "1992-02-27"
        )

        this.mockMvc
        .perform(
            patch("$url/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsBytes(request)))

    }

    @Test
    fun deleteMember() {
        this.mockMvc
            .perform(
                delete("$url/1"))
    }
}