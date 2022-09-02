package br.com.marchiro.devhub.data.repository

import br.com.marchiro.devhub.data.remote.dataSource.GitHubRemoteDataSource
import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import br.com.marchiro.devhub.data.remote.dto.GitHubRepositoryDTO
import br.com.marchiro.devhub.domain.repository.GitHubRepository
import br.com.marchiro.devhub.domain.repository.GitHubResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubRepositoryImpl(private val gitHubRemoteDataSource: GitHubRemoteDataSource)
    : GitHubRepository {
    private val _userInfoFlow = MutableStateFlow<GitHubResource<GitHubProfileDTO?>>(GitHubResource(null, null))
    private val _userRepoFlow = MutableStateFlow<GitHubResource<List<GitHubRepositoryDTO>?>>(GitHubResource(null, null))

    override fun searchUserInfo(user: String): Flow<GitHubResource<GitHubProfileDTO?>> {
        _userInfoFlow.value = GitHubResource(data = _userInfoFlow.value.data,  erro = null, loading = true)
        gitHubRemoteDataSource.getUser(user).enqueue(object: Callback<GitHubProfileDTO> {
            override fun onResponse(
                call: Call<GitHubProfileDTO>,
                response: Response<GitHubProfileDTO>
            ) {
                if (response.isSuccessful) {
                    _userInfoFlow.value = GitHubResource(data = response.body())
                } else {
                    _userInfoFlow.value = GitHubResource(data = _userInfoFlow.value.data,  erro = "Ocorreu algum erro")
                }
            }

            override fun onFailure(call: Call<GitHubProfileDTO>, t: Throwable) {
                _userInfoFlow.value = GitHubResource(data = _userInfoFlow.value.data,  erro = "Ocorreu algum erro")
            }

        })
        return _userInfoFlow
    }

    override fun readUserInfo(): Flow<GitHubResource<GitHubProfileDTO?>> = _userInfoFlow


    override fun searchUserRepo(user: String): Flow<GitHubResource<List<GitHubRepositoryDTO>?>> {
        _userRepoFlow.value = GitHubResource(data = null,  erro = null, loading = true)
        gitHubRemoteDataSource.getRepos(user).enqueue(object: Callback<List<GitHubRepositoryDTO>> {
            override fun onResponse(
                call: Call<List<GitHubRepositoryDTO>>,
                response: Response<List<GitHubRepositoryDTO>>
            ) {
                if (response.isSuccessful) {
                    _userRepoFlow.value = GitHubResource(data = response.body())
                } else {
                    _userRepoFlow.value = GitHubResource(data = _userRepoFlow.value.data,  erro = "Ocorreu algum erro")
                }
            }

            override fun onFailure(call: Call<List<GitHubRepositoryDTO>>, t: Throwable) {
                _userRepoFlow.value = GitHubResource(data = _userRepoFlow.value.data,  erro = "Ocorreu algum erro")
            }

        })
        return _userRepoFlow
    }

    override fun readUserRepo(): Flow<GitHubResource<List<GitHubRepositoryDTO>?>> = _userRepoFlow

}