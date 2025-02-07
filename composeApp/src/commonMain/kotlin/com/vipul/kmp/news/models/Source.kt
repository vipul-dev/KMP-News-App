package com.vipul.kmp.news.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Source(
//    @SerialName("id")
//    val id:String?,
    @SerialName("name")
    val name:String
)
