package br.com.marchiro.devhub.domain.repository

import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    fun getUserInfo(user: String) : Flow<GitHubResource<GitHubProfileDTO?>>
    fun readUserInfo() : Flow<GitHubResource<GitHubProfileDTO?>>
}