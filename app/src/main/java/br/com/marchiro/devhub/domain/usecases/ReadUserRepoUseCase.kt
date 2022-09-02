package br.com.marchiro.devhub.domain.usecases

import br.com.marchiro.devhub.data.remote.dto.GitHubRepositoryDTO
import br.com.marchiro.devhub.domain.repository.GitHubRepository
import br.com.marchiro.devhub.domain.repository.GitHubResource
import kotlinx.coroutines.flow.Flow

class ReadUserRepoUseCase(private val repository: GitHubRepository) {
    operator fun invoke() : Flow<GitHubResource<List<GitHubRepositoryDTO>?>> = repository.readUserRepo()
}