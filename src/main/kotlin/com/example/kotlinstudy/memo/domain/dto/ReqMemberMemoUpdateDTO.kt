package com.example.kotlinstudy.memo.domain.dto

import io.swagger.v3.oas.annotations.media.Schema

class ReqMemberMemoUpdateDTO (
    @field:Schema(title = "제목")
    var title : String? = null,
    @field:Schema(title = "본문")
    var content : String? = null,
    @field:Schema(title = "member ID")
    var memberId : Long? = null
) {
    @field:Schema(title = "ID")
    var id: String? = null

    fun setId(id: String?): ReqMemberMemoUpdateDTO {
        this.id = id
        return this
    }
}
