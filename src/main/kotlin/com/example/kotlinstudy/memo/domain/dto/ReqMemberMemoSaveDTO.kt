package com.example.kotlinstudy.memo.domain.dto

import io.swagger.v3.oas.annotations.media.Schema

class ReqMemberMemoSaveDTO(

    @field:Schema(title = "제목")
    var title : String? = null,
    @field:Schema(title = "본문")
    var content : String? = null,
    @field:Schema(title = "member ID")
    var memberId : Long? = null
)
