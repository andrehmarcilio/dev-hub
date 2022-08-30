package br.com.marchiro.devhub.data.repository

import br.com.marchiro.devhub.data.remote.dataSource.GitHubRemoteDataSource
import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import br.com.marchiro.devhub.domain.repository.GitHubRepository
import retrofit2.Call

class GitHubRepositoryImpl(private val gitHubRemoteDataSource: GitHubRemoteDataSource)
    : GitHubRepository {
    override fun getUserInfo(): Call<GitHubProfileDTO> = gitHubRemoteDataSource.getUser()
}