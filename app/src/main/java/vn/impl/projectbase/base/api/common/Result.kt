package vn.impl.projectbase.base.api.common

data class Result<out T>(
    val data:T?,
    val error:Throwable?
)
