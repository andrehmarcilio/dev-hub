package br.com.marchiro.devhub.domain.usecases

import br.com.marchiro.devhub.data.remote.dto.GitHubRepositoryDTO
import br.com.marchiro.devhub.domain.repository.GitHubRepository
import br.com.marchiro.devhub.domain.repository.GitHubResource
import kotlinx.coroutines.flow.Flow

class SearchUserRepoUseCase(private val gitHubRepository: GitHubRepository) {
    operator fun invoke(user: String) : Flow<GitHubResource<List<GitHubRepositoryDTO>?>> =
        gitHubRepository.searchUserRepo(user)
}