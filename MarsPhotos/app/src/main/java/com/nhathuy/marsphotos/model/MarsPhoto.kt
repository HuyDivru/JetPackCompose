package com.nhathuy.marsphotos.model

import androidx.annotation.DrawableRes
import kotlinx.serialization.SerialName

data class MarsPhoto(
    val id:String,
    @SerialName(value = "img_src")
    val imageMars:String
)