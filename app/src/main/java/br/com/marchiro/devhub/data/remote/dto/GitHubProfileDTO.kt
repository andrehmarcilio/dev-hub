package br.com.marchiro.devhub.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GitHubProfileDTO(
    @SerializedName("name") val name: String,
    @SerializedName("avatar_url") val img: String,
    @SerializedName("login") val login: String,
    @SerializedName("bio") val bio: String,
) : Serializable
