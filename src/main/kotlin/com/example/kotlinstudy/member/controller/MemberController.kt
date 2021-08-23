package com.example.kotlinstudy.member.controller

import ReqMemberUpdateDTO
import ResMemberDTO
import com.example.kotlinstudy.common.config.domain.CommonResponse
import com.example.kotlinstudy.member.domain.dto.ReqMemberSaveDTO
import com.example.kotlinstudy.member.service.MemberService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/member")
@Tag(name = "[회원]", description = "MemberController")
class MemberController(
    val memberService: MemberService
){

    @Operation(summary = "회원 정보 조회")
    @GetMapping("/{id}")
    fun getMember(@PathVariable id: Long) : CommonResponse<ResMemberDTO> {
        return memberService.findById(id)
    }

    @Operation(summary = "회원 정보 저장")
    @PostMapping
    fun saveMember(@RequestBody @Valid memberSaveDTO: ReqMemberSaveDTO) : CommonResponse<ResMemberDTO> {
        return memberService.saveMember(memberSaveDTO)
    }

    @Operation(summary = "회원 정보 수정")
    @PatchMapping("/{id}")
    fun updateMember(
        @Parameter(description = "member ID")@PathVariable id: Long,
        @RequestBody @Valid memberUpdateDTO: ReqMemberUpdateDTO
    ) : CommonResponse<ResMemberDTO> {
        return memberService.updateMember(memberUpdateDTO.setId(id))
    }

    @Operation(summary = "회원 정보 삭제")
    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id: Long) : ResponseEntity<Any> {
        return ResponseEntity.ok(memberService.deleteById(id))
    }
}