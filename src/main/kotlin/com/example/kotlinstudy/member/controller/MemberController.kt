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
// API 그룹 설정 및 태그 설명(name 같으면 그룹으로 설정)
@Tag(name = "[회원]", description = "MemberController")
class MemberController(
    val memberService: MemberService
){
    /**
     * summary : API 간략 설명
     * description : API 대한 상세 설명
     * responses : API Response 리스트
     * parameters : API 파라미터 리스트
    */
    @Operation(summary = "회원 정보 조회") //API 상세 정보
    @GetMapping("/{id}")
    fun getMember(@PathVariable id: Long) = memberService.findById(id)

    @Operation(summary = "회원 정보 저장")
    @PostMapping
    fun saveMember(@RequestBody memberSaveDTO: ReqMemberSaveDTO) = memberService.saveMember(memberSaveDTO)

    @Operation(summary = "회원 정보 수정")
    @PatchMapping("/{id}")
    fun updateMember(
        @PathVariable id: Long,
        @RequestBody @Valid memberUpdateDTO: ReqMemberUpdateDTO) = memberService.updateMember(memberUpdateDTO.setId(id))

    @Operation(summary = "회원 정보 삭제")
    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id: Long) : ResponseEntity<Any> = ResponseEntity.ok(memberService.deleteById(id))

}