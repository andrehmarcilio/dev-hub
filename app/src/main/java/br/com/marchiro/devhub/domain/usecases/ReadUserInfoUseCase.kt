package br.com.marchiro.devhub.domain.usecases

import br.com.marchiro.devhub.domain.repository.GitHubRepository

class ReadUserInfoUseCase(private val gitHubRepository: GitHubRepository) {
    operator fun invoke() = gitHubRepository.readUserInfo()
}