package br.com.marchiro.devhub.ui.states

import br.com.marchiro.devhub.data.remote.dto.GitHubRepositoryDTO

data class ProfileUiState(
    val user: String?,
    val image: String?,
    val name: String?,
    val bio: String?,
    val repositories: List<GitHubRepositoryDTO>? = emptyList()
)
