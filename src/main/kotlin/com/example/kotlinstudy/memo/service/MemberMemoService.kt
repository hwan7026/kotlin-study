package com.example.kotlinstudy.memo.service

import com.example.kotlinstudy.common.config.domain.CommonResponse
import com.example.kotlinstudy.common.exception.DataNotFountException
import com.example.kotlinstudy.memo.domain.dto.ReqMemberMemoSaveDTO
import com.example.kotlinstudy.memo.domain.dto.ReqMemberMemoUpdateDTO
import com.example.kotlinstudy.memo.domain.dto.ResMemberMemoDTO
import com.example.kotlinstudy.memo.domain.entity.MemberMemo
import com.example.kotlinstudy.memo.repository.MemberMemoRepository
import org.bson.types.ObjectId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    @Transactional
    fun updateMemberMemoById(memberMemoUpdateDTO: ReqMemberMemoUpdateDTO) : CommonResponse<ResMemberMemoDTO> {
        val memberMemo = memberMemoRepository.findByIdOrNull(ObjectId(memberMemoUpdateDTO.id))
            ?: throw DataNotFountException("Data Not Fount")
        memberMemo.updateInfo(memberMemoUpdateDTO)
        return CommonResponse(ResMemberMemoDTO(memberMemo))
    }

    fun deleteMemberMemoById(id: String) {
       return memberMemoRepository.deleteById(ObjectId(id))
    }


}