package com.example.acronyms.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Lf(
    val freq: Int,
    val lf: String,
    val since: Int,
    val vars: List<Var>
)