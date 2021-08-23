package com.example.kotlinstudy.member.repository

import com.example.kotlinstudy.member.domain.entity.Member
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface MemberRepository : MongoRepository<Member, ObjectId>