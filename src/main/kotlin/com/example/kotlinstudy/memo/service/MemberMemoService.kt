package com.example.kotlinstudy.memo.service

import com.example.kotlinstudy.common.config.domain.CommonResponse
import com.example.kotlinstudy.memo.domain.dto.ReqMemberMemoSaveDTO
import com.example.kotlinstudy.memo.domain.dto.ReqMemberMemoUpdateDTO
import com.example.kotlinstudy.memo.domain.dto.ResMemberMemoDTO
import com.example.kotlinstudy.memo.domain.entity.MemberMemo
import com.example.kotlinstudy.memo.repository.MemberMemoRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class MemberMemoService(
    val memberMemoRepository: MemberMemoRepository
){
    fun findByMemberId(id: Long): List<MemberMemo> {
        return memberMemoRepository.findByMemberId(id)
    }

    fun saveMemberMemo(reqMemberMemoSaveDTO: ReqMemberMemoSaveDTO) :CommonResponse<ResMemberMemoDTO> {
        val memberMemo = memberMemoRepository.save(MemberMemo(reqMemberMemoSaveDTO))
        return CommonResponse(ResMemberMemoDTO(memberMemo))
    }

    fun updateMemberMemoById(memberMemoUpdateDTO: ReqMemberMemoUpdateDTO) : CommonResponse<ResMemberMemoDTO> {
        val memberMemo = memberMemoRepository.save(MemberMemo(memberMemoUpdateDTO))
        return CommonResponse(ResMemberMemoDTO(memberMemo))
    }

    fun deleteMemberMemoById(id: String) {
       return memberMemoRepository.deleteById(ObjectId(id))
    }


}