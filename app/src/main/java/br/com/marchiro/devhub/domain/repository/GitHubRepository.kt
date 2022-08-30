package br.com.marchiro.devhub.domain.repository

import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import retrofit2.Call

interface GitHubRepository {
    fun getUserInfo() : Call<GitHubProfileDTO>
}