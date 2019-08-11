package vn.impl.projectbase.base.api.user.response

import com.squareup.moshi.Json

data class GetResponse(

    @field:Json(name = "access_token")
    val accessToken: String?
)
