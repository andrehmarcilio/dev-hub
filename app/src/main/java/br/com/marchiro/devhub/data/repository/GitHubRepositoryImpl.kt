package br.com.marchiro.devhub.data.repository

import br.com.marchiro.devhub.data.remote.dataSource.GitHubRemoteDataSource
import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import br.com.marchiro.devhub.domain.repository.GitHubRepository
import br.com.marchiro.devhub.ui.states.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubRepositoryImpl(private val gitHubRemoteDataSource: GitHubRemoteDataSource)
    : GitHubRepository {
    private val _userInfoFlow = MutableStateFlow<State<GitHubProfileDTO?>>(State.InitialState)

    override fun getUserInfo(user: String): Flow<State<GitHubProfileDTO?>> {
        _userInfoFlow.value = State.LoadingState
        gitHubRemoteDataSource.getUser(user).enqueue(object: Callback<GitHubProfileDTO> {
            override fun onResponse(
                call: Call<GitHubProfileDTO>,
                response: Response<GitHubProfileDTO>
            ) {
                if (response.isSuccessful) {
                    _userInfoFlow.value = State.DataState(data = response.body())
                } else {
                    _userInfoFlow.value = State.ErrorState(exception = "Ocorreu algum Erro")
                }
            }

            override fun onFailure(call: Call<GitHubProfileDTO>, t: Throwable) {
                _userInfoFlow.value = State.ErrorState(exception = "Ocorreu algum Erro")
            }

        })
        return _userInfoFlow
    }

    override fun readUserInfo(): Flow<State<GitHubProfileDTO?>> = _userInfoFlow

}