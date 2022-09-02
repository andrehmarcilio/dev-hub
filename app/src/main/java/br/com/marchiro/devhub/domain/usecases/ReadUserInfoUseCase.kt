package br.com.marchiro.devhub.domain.usecases

import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import br.com.marchiro.devhub.domain.repository.GitHubRepository
import br.com.marchiro.devhub.domain.repository.GitHubResource
import kotlinx.coroutines.flow.Flow

class ReadUserInfoUseCase(private val gitHubRepository: GitHubRepository) {
    operator fun invoke(): Flow<GitHubResource<GitHubProfileDTO?>> = gitHubRepository.readUserInfo()
}