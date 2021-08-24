package com.example.kotlinstudy.memo.domain.dto

import com.example.kotlinstudy.memo.domain.entity.MemberMemo
import io.swagger.v3.oas.annotations.media.Schema

class ResMemberMemoDTO (

    @field:Schema(title = "ID")
    var id : String? = null,
    @field:Schema(title = "제목")
    var title : String? = null,
    @field:Schema(title = "본문")
    var content : String? = null,
    @field:Schema(title = "member ID")
    var memberId : Long? = null
) {
    constructor(memberMemo: MemberMemo) : this(
        id = memberMemo.id.toString(),
        title = memberMemo.title,
        content = memberMemo.content,
        memberId = memberMemo.memberId,
    )
}