package com.example.kotlinstudy.member.repository

import com.example.kotlinstudy.member.domain.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>