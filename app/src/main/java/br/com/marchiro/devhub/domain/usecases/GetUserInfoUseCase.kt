package br.com.marchiro.devhub.domain.usecases

import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import br.com.marchiro.devhub.domain.repository.GitHubRepository
import br.com.marchiro.devhub.ui.states.State
import kotlinx.coroutines.flow.Flow

class GetUserInfoUseCase(private val gitHubRepository: GitHubRepository) {
    operator fun invoke(user: String) : Flow<State<GitHubProfileDTO?>> = gitHubRepository.getUserInfo(user)
}