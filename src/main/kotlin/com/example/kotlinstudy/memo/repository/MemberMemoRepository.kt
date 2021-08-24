package com.example.kotlinstudy.memo.repository

import com.example.kotlinstudy.memo.domain.entity.MemberMemo
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface MemberMemoRepository : MongoRepository<MemberMemo, ObjectId> {
    fun findByMemberId(id: Long) : List<MemberMemo>
}