package com.imandroid.streambox.features.home.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeContentDto(
    @SerialName("name")
    val name: String,
    @SerialName("premiered")
    val premiered: String? = null,
    @SerialName("genres")
    val genres: List<String> = emptyList()
)
