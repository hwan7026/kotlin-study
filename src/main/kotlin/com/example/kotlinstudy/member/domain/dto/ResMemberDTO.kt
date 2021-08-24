

import com.example.kotlinstudy.member.domain.entity.Member
import com.example.kotlinstudy.member.domain.enums.GenderType
import com.sun.istack.NotNull
import io.swagger.v3.oas.annotations.media.Schema
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.NotEmpty

data class ResMemberDTO(

    var id : Long? = null,
    @field:Schema(title = "이메일")
    @field:NotNull
    var email : String? = null,
    @field:NotEmpty
    @field:Schema(title = "이름")
    var name : String? = null,
    @Enumerated(EnumType.STRING)
    @field:Schema(title = "성별")
    var gender : GenderType? = null,
    @field:Schema(title = "생년월일")
    var birthday : String? = null,
    @field:Schema(title = "핸드폰 번호")
    var phone : String? = null
) {
    constructor(member: Member) :this(
        id = member.id,
        email = member.email,
        name = member.name,
        gender = member.gender,
        birthday = member.birthday,
        phone = member.phone
    )

}
