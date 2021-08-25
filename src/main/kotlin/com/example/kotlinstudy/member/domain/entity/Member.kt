package com.example.kotlinstudy.member.domain.entity

import com.example.kotlinstudy.member.domain.dto.ReqMemberUpdateDTO
import com.example.kotlinstudy.common.domain.entity.BaseIncludeDeleteEntity
import com.example.kotlinstudy.member.domain.dto.ReqMemberSaveDTO
import com.example.kotlinstudy.member.domain.enums.GenderType
import io.swagger.v3.oas.annotations.media.Schema
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Schema(title = "고객 정보", hidden = true)
@Entity
class Member (

    @field:Schema(title = "고객 ID")
    @Id @GeneratedValue(strategy =GenerationType.IDENTITY)
    var id : Long? = 0,
    @field:Schema(title = "이메일")
    var email : String? = null,
    @field:Schema(title = "이름")
    var name : String? = null,
    @field:Schema(title = "성별")
    var gender : GenderType? = null,
    @field:Schema(title = "생년월일")
    var birthday : String? = null,
    @field:Schema(title = "핸드폰 번호")
    var phone : String? = null
) : BaseIncludeDeleteEntity() {

    constructor(memberSaveDTO: ReqMemberSaveDTO) :this(
        email = memberSaveDTO.email,
        name = memberSaveDTO.name,
        gender = memberSaveDTO.gender,
        birthday = memberSaveDTO.birthday,
        phone = memberSaveDTO.phone
    )
    constructor(memberUpdateDTO: ReqMemberUpdateDTO) :this(
        id = memberUpdateDTO.id,
        email = memberUpdateDTO.email,
        name = memberUpdateDTO.name,
        gender = memberUpdateDTO.gender,
        birthday = memberUpdateDTO.birthday
    )
}