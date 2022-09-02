package br.com.marchiro.devhub.data.remote.dataSource

import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import br.com.marchiro.devhub.data.remote.dto.GitHubRepositoryDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubRemoteDataSource {

    @GET("/users/{user}")
    fun getUser(@Path("user") user: String) : Call<GitHubProfileDTO>

    @GET("/users/{user}/repos")
    fun getRepos(@Path("user") user: String) : Call<List<GitHubRepositoryDTO>>
}