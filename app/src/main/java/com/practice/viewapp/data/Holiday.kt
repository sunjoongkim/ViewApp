package com.practice.viewapp.data

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Holiday(
    @SerializedName("localName") val localName: String,
    @SerializedName("name") val name: String,
    @SerializedName("date") val date: String,
    @SerializedName("url") val url: String?,
)
