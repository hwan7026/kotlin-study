package com.example.kotlinstudy.memo.controller

import com.example.kotlinstudy.memo.domain.dto.ReqMemberMemoSaveDTO
import com.example.kotlinstudy.memo.domain.dto.ReqMemberMemoUpdateDTO
import com.example.kotlinstudy.memo.service.MemberMemoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/member/memo")
@Tag(name = "[회원 메모]", description = "MemberMemoController")
class MemberMemoController (
    val memberMemoService: MemberMemoService
){

    @Operation(summary = "회원 메모 조회")
    @GetMapping("/{id}")
    fun getMemberMemo(@PathVariable id: Long) = memberMemoService.findByMemberId(id)

    @Operation(summary = "회원 메모 저장")
    @PostMapping
    fun saveMemberMemo(@RequestBody reqMemberMemoSaveDTO: ReqMemberMemoSaveDTO)
        = memberMemoService.saveMemberMemo(reqMemberMemoSaveDTO)

    @Operation(summary = "회원 메모 수정")
    @PatchMapping("/{id}")
    fun updateMemberMemo(@PathVariable id: String, @RequestBody @Valid reqMemberMemoUpdateDTO : ReqMemberMemoUpdateDTO)
        = memberMemoService.updateMemberMemoById(reqMemberMemoUpdateDTO.setId(id))

    @Operation(summary = "회원 메모 삭제")
    @DeleteMapping("/{id}")
    fun deleteMemberMemo(@PathVariable id: String) : ResponseEntity<Any>
        = ResponseEntity.ok(memberMemoService.deleteMemberMemoById(id))

}