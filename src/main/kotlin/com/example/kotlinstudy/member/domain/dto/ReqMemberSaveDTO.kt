package com.example.kotlinstudy.member.domain.dto

import com.example.kotlinstudy.member.domain.enums.GenderType
import com.sun.istack.NotNull
import io.swagger.v3.oas.annotations.media.Schema
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.NotEmpty

class ReqMemberSaveDTO (

    /* @Schema : swagger 모델에 대한 추가 정보 제공*/
    @field:NotNull
    @field:Schema(title = "이메일")
    var email : String? = null,
    @field:NotEmpty
    @field:Schema(title = "비밀번호")
    var password : String? = null,
    @field:Schema(title = "이름")
    var name : String? = null,
    @Enumerated(EnumType.STRING)
    @field:Schema(title = "성별")
    var gender : GenderType? = null,
    @field:Schema(title = "생년월일")
    var birthday : String? = null,
    @field:Schema(title = "핸드폰 번호")
    var phone : String? = null
)