package br.com.marchiro.devhub.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import br.com.marchiro.devhub.domain.usecases.GetUserInfoUseCase
import br.com.marchiro.devhub.ui.states.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(private val getUserInfoUseCase: GetUserInfoUseCase) : ViewModel() {

    private val _userStateFlow: MutableStateFlow<State<GitHubProfileDTO?>> =
        MutableStateFlow(State.InitialState)
    val userStateFlow: StateFlow<State<GitHubProfileDTO?>> = _userStateFlow

    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            _userStateFlow.value = State.LoadingState
            getUserInfoUseCase().enqueue(object: Callback<GitHubProfileDTO> {
                override fun onResponse(
                    call: Call<GitHubProfileDTO>,
                    response: Response<GitHubProfileDTO>
                ) {
                    if (response.isSuccessful) {
                        _userStateFlow.value = State.DataState(data = response.body())
                    } else {
                        _userStateFlow.value = State.ErrorState(exception = "Ocorreu algum Erro")
                    }
                }

                override fun onFailure(call: Call<GitHubProfileDTO>, t: Throwable) {
                    _userStateFlow.value = State.ErrorState(exception = "Ocorreu algum Erro")
                }

            })
        }
    }
}