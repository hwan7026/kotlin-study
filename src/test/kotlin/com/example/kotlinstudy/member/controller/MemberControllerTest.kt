package com.example.kotlinstudy.member.controller

import ReqMemberUpdateDTO
import com.example.kotlinstudy.member.domain.dto.ReqMemberSaveDTO
import com.example.kotlinstudy.member.domain.entity.Member
import com.example.kotlinstudy.member.domain.enums.GenderType
import com.example.kotlinstudy.member.repository.MemberRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.bson.types.ObjectId
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
    val webApplicationContext : WebApplicationContext,
){

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    val MEMBER_URL = "/api/v1/member"

    private lateinit var defaultProductObjectId: ObjectId

    @BeforeEach
    fun beforeSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilters<DefaultMockMvcBuilder>(
            CharacterEncodingFilter("UTF-8", true))
            .build()

        val member = memberRepository.save(
            Member(
                ObjectId.get(),
                "test@email.com",
                "이승환",
                GenderType.MAN,
                "1992-02-27",
                "010-4331-7026"
            )
        )
        defaultProductObjectId = member.id!!
    }

    @DisplayName("회원 정보 확인 테스트")
    @Test
    fun getMemberTest() {
        mockMvc.perform(
            get("$MEMBER_URL/$defaultProductObjectId")
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
                post(MEMBER_URL)
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
            patch("$MEMBER_URL/$defaultProductObjectId")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsBytes(request)))
            .andExpect(status().isOk)

    }

    @Test
    fun deleteMember() {
        this.mockMvc
            .perform(
                delete("$MEMBER_URL/$defaultProductObjectId"))
            .andExpect(status().isOk)
    }
}