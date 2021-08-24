package com.example.kotlinstudy.memo.controller

import com.example.kotlinstudy.memo.domain.dto.ReqMemberMemoSaveDTO
import com.example.kotlinstudy.memo.domain.dto.ReqMemberMemoUpdateDTO
import com.example.kotlinstudy.memo.domain.entity.MemberMemo
import com.example.kotlinstudy.memo.repository.MemberMemoRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.bson.types.ObjectId
import org.junit.jupiter.api.BeforeEach
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
class MemberMemoControllerTest(
    val memberMemoRepository: MemberMemoRepository,
    val webApplicationContext: WebApplicationContext
) {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    val MEMBER_MEMO_URL = "/api/v1/member/memo"

    var defaultMemberMemoObjectId: ObjectId? = null

    @BeforeEach
    fun beforeSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilters<DefaultMockMvcBuilder>(
                CharacterEncodingFilter("UTF-8", true)
            )
            .build()

        val memberMemo = memberMemoRepository.save(
            MemberMemo(
                "title",
                "content",
                1L
            )
        )
        defaultMemberMemoObjectId = memberMemo.id
    }


    @Test
    fun getMemberMemo(){
        mockMvc.perform(
            get("$MEMBER_MEMO_URL/1")
        ).andDo(print())
    }

    @Test
    fun saveMemberMemo(){
        val memberMemoSaveDTO = ReqMemberMemoSaveDTO(
            "title",
            "content",
            1L
        )
        mockMvc.perform(
            post(MEMBER_MEMO_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(memberMemoSaveDTO))
        ).andExpect(status().isOk)
        .andDo(print())
    }

    @Test
    fun updateMemberMemo(){
        val memberMemoSaveDTO = ReqMemberMemoUpdateDTO(
            "title modify",
            "content modify",
            1L
        )
        mockMvc.perform(
            patch("$MEMBER_MEMO_URL/$defaultMemberMemoObjectId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(memberMemoSaveDTO))
        ).andExpect(status().isOk).andDo(print())
    }

    @Test
    fun `회원메모삭제`(){
        mockMvc.perform(
            delete("$MEMBER_MEMO_URL/$defaultMemberMemoObjectId")
        ).andExpect(status().isOk)
        .andDo(print())
    }

}