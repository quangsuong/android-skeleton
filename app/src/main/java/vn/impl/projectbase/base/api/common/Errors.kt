package vn.impl.projectbase.base.api.common

import com.squareup.moshi.Json

data class Errors(

        /**
         * Confirm with server to define errors response
         */

        @field:Json(name = "error_message")
        val errorMessage: String?,

        @field:Json(name = "error_code")
        val errorCode: Int?
)
