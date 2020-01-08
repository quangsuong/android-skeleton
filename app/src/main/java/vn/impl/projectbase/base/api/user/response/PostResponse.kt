package vn.impl.projectbase.base.api.user.response

import com.squareup.moshi.Json

data class PostResponse(

    @field:Json(name = "result_code")
    val resultCode: Int?,

    @field:Json(name = "result_message")
    val resultMessage: String?
)
