package br.com.marchiro.devhub.data.remote.dto

import java.io.Serializable

data class GitHubRepositoryDTO(
    val name: String? = "",
    val description: String? = null
) : Serializable
