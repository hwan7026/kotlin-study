package com.example.kotlinstudy.common.config.domain

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus

@Schema(title = "공통 응답")
class CommonResponse<T> (

    @field:Schema(title = "응답 코드", example = "200")
    val code : Int,
    @field:Schema(title = "응답 메세지", example = "success")
    val message : String,
    @field:Schema(title = "응답 데이터")
    val content : T?
) {

    constructor(content: T) : this(HttpStatus.OK.value(), HttpStatus.OK.reasonPhrase, content)

    constructor(httpStatus: HttpStatus, message: String? = null) : this( httpStatus.value(), message ?: httpStatus.reasonPhrase, null)

    override fun toString(): String {
        return "CommonResponse(code=$code, message='$message', content=$content)"
    }
}