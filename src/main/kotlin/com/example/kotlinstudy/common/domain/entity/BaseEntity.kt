package com.example.kotlinstudy.common.domain.entity

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

/**
 * @MappedSuperclass : 공통 정보으로 가져야하는 정보를 상속받기 위해 사용 (엔티티는 엔티티만 상속 가능) / 기본 값은 createDate, modifiedDate 컬럼으로 인식
 * @EntityListeners(AuditingEntityListener::class) : 해당 클래스에 Auditing 기능 포함
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity (

    @field:Schema(title = "등록일시")
    @CreatedDate
    var createdAt: LocalDateTime? = null,

    @field:Schema(title = "수정일시")
    @LastModifiedDate
    var updatedAt: LocalDateTime? = null,
)