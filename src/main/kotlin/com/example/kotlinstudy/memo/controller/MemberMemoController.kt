package com.example.kotlinstudy.memo.controller

import com.example.kotlinstudy.memo.domain.dto.ReqMemberMemoSaveDTO
import com.example.kotlinstudy.memo.domain.dto.ReqMemberMemoUpdateDTO
import com.example.kotlinstudy.memo.service.MemberMemoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/member/memo")
class MemberMemoController (
    val memberMemoService: MemberMemoService
){

    @GetMapping("/{id}")
    fun getMemberMemo(@PathVariable id: Long) = memberMemoService.findByMemberId(id)

    @PostMapping
    fun saveMemberMemo(@RequestBody reqMemberMemoSaveDTO: ReqMemberMemoSaveDTO)
        = memberMemoService.saveMemberMemo(reqMemberMemoSaveDTO)

    @PatchMapping("/{id}")
    fun updateMemberMemo(@PathVariable id: String, @RequestBody reqMemberMemoUpdateDTO : ReqMemberMemoUpdateDTO)
    = memberMemoService.updateMemberMemoById(reqMemberMemoUpdateDTO.setId(id))

    @DeleteMapping("/{id}")
    fun deleteMemberMemo(@PathVariable id: String) : ResponseEntity<Any> =
        ResponseEntity.ok(memberMemoService.deleteMemberMemoById(id))

}