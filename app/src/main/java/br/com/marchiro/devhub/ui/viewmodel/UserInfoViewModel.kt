package br.com.marchiro.devhub.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import br.com.marchiro.devhub.domain.usecases.ReadUserInfoUseCase
import br.com.marchiro.devhub.ui.states.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserInfoViewModel(private val readUserInfoUseCase: ReadUserInfoUseCase) : ViewModel() {

    private val _userStateFlow: MutableStateFlow<State<GitHubProfileDTO?>> =
        MutableStateFlow(State.InitialState)
    val userStateFlow: StateFlow<State<GitHubProfileDTO?>> = _userStateFlow

    init {
        getUser()
    }

    private fun getUser()  {
        viewModelScope.launch(Dispatchers.IO) {
            _userStateFlow.value = State.LoadingState
              readUserInfoUseCase().collect {
             _userStateFlow.value = it
            }
        }
    }
}