package com.example.kotlinstudy.member.domain.entity

import ReqMemberUpdateDTO
import com.example.kotlinstudy.member.domain.dto.ReqMemberSaveDTO
import com.example.kotlinstudy.member.domain.enums.GenderType
import com.querydsl.core.annotations.QueryEntity
import io.swagger.v3.oas.annotations.media.Schema
import lombok.Setter
import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDateTime
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
    var phone : String? = null,
    @field:Schema(title = "수정일시")
    @LastModifiedDate
    var updatedAt : LocalDateTime? = null,
    @field:Schema(title = "생성일")
    @CreatedDate
    var createdAt : LocalDateTime? = null

) {

    @Setter
    @field:Schema(title = "고객 삭제 여부")
    var deleteFlag : Boolean? = false
    @Setter
    @field:Schema(title = "삭제 일시")
    var deletedAt : LocalDateTime? = null

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