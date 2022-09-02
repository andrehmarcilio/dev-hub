package br.com.marchiro.devhub.domain.usecases

import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import br.com.marchiro.devhub.domain.repository.GitHubRepository
import br.com.marchiro.devhub.domain.repository.GitHubResource
import kotlinx.coroutines.flow.Flow

class SearchUserInfoUseCase(private val gitHubRepository: GitHubRepository) {
    operator fun invoke(user: String) : Flow<GitHubResource<GitHubProfileDTO?>> = gitHubRepository.searchUserInfo(user)
}