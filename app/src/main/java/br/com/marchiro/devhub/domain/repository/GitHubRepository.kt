package br.com.marchiro.devhub.domain.repository

import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import br.com.marchiro.devhub.data.remote.dto.GitHubRepositoryDTO
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    fun searchUserInfo(user: String) : Flow<GitHubResource<GitHubProfileDTO?>>
    fun readUserInfo() : Flow<GitHubResource<GitHubProfileDTO?>>
    fun searchUserRepo(user: String) : Flow<GitHubResource<List<GitHubRepositoryDTO>?>>
    fun readUserRepo() : Flow<GitHubResource<List<GitHubRepositoryDTO>?>>
}