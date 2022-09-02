package br.com.marchiro.devhub.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import br.com.marchiro.devhub.data.remote.dto.GitHubRepositoryDTO
import br.com.marchiro.devhub.domain.repository.GitHubResource
import br.com.marchiro.devhub.domain.usecases.ReadUserInfoUseCase
import br.com.marchiro.devhub.domain.usecases.ReadUserRepoUseCase
import br.com.marchiro.devhub.ui.states.ProfileUiState
import br.com.marchiro.devhub.ui.states.State
import br.com.marchiro.devhub.util.extensions.toProfileUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserInfoViewModel(
    private val readUserInfoUseCase: ReadUserInfoUseCase,
    private val readUserRepoUseCase: ReadUserRepoUseCase) : ViewModel() {

    var userStateFlow by mutableStateOf<State<ProfileUiState?>>(State.InitialState)
        private set


    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                userStateFlow = State.LoadingState
            }
            readUserInfoUseCase().collect { resource : GitHubResource<GitHubProfileDTO?> ->
                withContext(Dispatchers.Main) {
                    userStateFlow = if(resource.loading) {
                        State.LoadingState
                    } else {
                        if (resource.erro != null) {
                            State.ErrorState(exception = resource.erro)
                        } else {
                            State.DataState(resource.data?.toProfileUiState())
                        }
                    }
                }

                if(userStateFlow is State.DataState<ProfileUiState?>) {
                    getRepo(userStateFlow as State.DataState<ProfileUiState?>)
                }
            }
        }
    }

    private fun getRepo(state: State.DataState<ProfileUiState?>) {
        viewModelScope.launch(Dispatchers.IO) {
            readUserRepoUseCase().collect { resource : GitHubResource<List<GitHubRepositoryDTO>?> ->
                withContext(Dispatchers.Main) {
                    Log.i("UserInfoViewMLegal", "getRepo: ${state.data?.copy(repositories = resource.data)}")
                    userStateFlow = State.DataState(state.data?.copy(repositories = resource.data))
                }
            }

        }
    }
}