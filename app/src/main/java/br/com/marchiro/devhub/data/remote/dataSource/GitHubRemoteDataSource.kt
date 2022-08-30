package br.com.marchiro.devhub.data.remote.dataSource

import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import retrofit2.Call
import retrofit2.http.GET

interface GitHubRemoteDataSource {

    @GET("/users/andrehmarcilio")
    fun getUser() : Call<GitHubProfileDTO>
}