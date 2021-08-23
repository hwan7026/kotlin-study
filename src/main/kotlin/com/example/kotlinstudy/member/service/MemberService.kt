package com.example.kotlinstudy.member.service

import ReqMemberUpdateDTO
import ResMemberDTO
import com.example.kotlinstudy.common.config.domain.CommonResponse
import com.example.kotlinstudy.common.exception.DataNotFountException
import com.example.kotlinstudy.member.domain.dto.ReqMemberSaveDTO
import com.example.kotlinstudy.member.domain.entity.Member
import com.example.kotlinstudy.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MemberService(
    val memberRepository: MemberRepository
) {

    fun findById(id: Long): CommonResponse<ResMemberDTO> {
        val member = memberRepository.findByIdOrNull(id) ?: throw DataNotFountException("Data Not Found")
        return CommonResponse(ResMemberDTO(member))
    }

    fun saveMember(memberSaveDTO: ReqMemberSaveDTO): CommonResponse<ResMemberDTO> {
        val member = memberRepository.save(Member(memberSaveDTO))
        return CommonResponse(ResMemberDTO(member))
    }

    fun updateMember(memberUpdateDTO: ReqMemberUpdateDTO): CommonResponse<ResMemberDTO> {
        memberRepository.findByIdOrNull(memberUpdateDTO.id) ?: throw DataNotFountException("Data Not Found")
        val member = memberRepository.save(Member(memberUpdateDTO))
        return CommonResponse(ResMemberDTO(member))
    }

    fun deleteById(id: Long) {
        memberRepository.findByIdOrNull(id)?.let {
            it.deletedAt = LocalDateTime.now()
            it.deleteFlag = true
            memberRepository.save(it)
        }
    }
}