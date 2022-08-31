package br.com.marchiro.devhub.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.marchiro.devhub.domain.usecases.GetUserInfoUseCase

class UserFormViewModel(private val getUserInfoUseCase: GetUserInfoUseCase) : ViewModel() {

    fun getUserInfo(user: String) = getUserInfoUseCase(user)
}