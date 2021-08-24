
import com.example.kotlinstudy.member.domain.enums.GenderType
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.sun.istack.NotNull
import io.swagger.v3.oas.annotations.media.Schema
import javax.persistence.EnumType
import javax.persistence.Enumerated

class ReqMemberUpdateDTO (

    @field:Schema(title = "이메일")
    var email : String? = null,
    @field:Schema(title = "이름")
    var name : String? = null,
    @Enumerated(EnumType.STRING)
    @field:Schema(title = "성별")
    var gender : GenderType? = null,
    @field:Schema(title = "생년월일")
    var birthday : String? = null,

) {

    @field:Schema(title = "member ID")
    var id : Long? = null

    fun setId(id: Long): ReqMemberUpdateDTO {
        this.id = id
        return this
    }
}