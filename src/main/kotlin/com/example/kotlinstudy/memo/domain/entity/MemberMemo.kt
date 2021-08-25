package com.example.kotlinstudy.memo.domain.entity

import com.example.kotlinstudy.common.domain.entity.BaseEntity
import com.example.kotlinstudy.memo.domain.dto.ReqMemberMemoSaveDTO
import com.example.kotlinstudy.memo.domain.dto.ReqMemberMemoUpdateDTO
import com.querydsl.core.annotations.QueryEntity
import io.swagger.v3.oas.annotations.media.Schema
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId

@Schema(title = "회원 메모", hidden = true)
@Document(collection="memberMemo")
@QueryEntity
class MemberMemo (

    @field:Schema(title = "제목")
    var title : String? = null,
    @field:Schema(title = "본문")
    var content : String? = null,
    @field:Schema(title = "member ID")
    var memberId : Long? = null,

    @field:Schema(title = "memo ID")
    @Schema(hidden = true)
    @MongoId
    var id : ObjectId? = null,
) : BaseEntity() {
    fun updateInfo(memberMemoUpdateDTO: ReqMemberMemoUpdateDTO) {
        this.title = memberMemoUpdateDTO.title
        this.content = memberMemoUpdateDTO.content
        this.memberId = memberMemoUpdateDTO.memberId
    }

    constructor(memberMemoDTO: ReqMemberMemoSaveDTO) : this(
        title = memberMemoDTO.title,
        content = memberMemoDTO.content,
        memberId = memberMemoDTO.memberId
    )
}