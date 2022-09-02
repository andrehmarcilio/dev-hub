package br.com.marchiro.devhub.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import br.com.marchiro.devhub.domain.repository.GitHubResource
import br.com.marchiro.devhub.domain.usecases.ReadUserInfoUseCase
import br.com.marchiro.devhub.ui.states.ProfileUiState
import br.com.marchiro.devhub.ui.states.State
import br.com.marchiro.devhub.util.extensions.toProfileUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserInfoViewModel(private val readUserInfoUseCase: ReadUserInfoUseCase) : ViewModel() {

    private val _userStateFlow: MutableStateFlow<State<ProfileUiState?>> =
        MutableStateFlow(State.InitialState)
    val userStateFlow: StateFlow<State<ProfileUiState?>> = _userStateFlow

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            _userStateFlow.value = State.LoadingState
            readUserInfoUseCase().collect { resource : GitHubResource<GitHubProfileDTO?> ->
                if(resource.loading) {
                    _userStateFlow.value = State.LoadingState
                } else {
                    if (resource.erro != null) {
                        _userStateFlow.value = State.ErrorState(exception = resource.erro)
                    } else {
                        _userStateFlow.value = State.DataState(resource.data?.toProfileUiState())
                    }
                }
            }
        }
    }
}