package br.com.marchiro.devhub.domain.usecases

import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import br.com.marchiro.devhub.domain.repository.GitHubRepository
import retrofit2.Call

class GetUserInfoUseCase(private val gitHubRepository: GitHubRepository) {
    operator fun invoke() : Call<GitHubProfileDTO> = gitHubRepository.getUserInfo()
}