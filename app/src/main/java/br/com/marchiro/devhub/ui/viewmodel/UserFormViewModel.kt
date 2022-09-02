package br.com.marchiro.devhub.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.marchiro.devhub.domain.usecases.SearchUserInfoUseCase
import br.com.marchiro.devhub.domain.usecases.SearchUserRepoUseCase

class UserFormViewModel(
    private val searchUserInfoUseCase: SearchUserInfoUseCase,
    private val searchUserRepoUseCase: SearchUserRepoUseCase) : ViewModel() {

    fun searchUserInfo(user: String) = searchUserInfoUseCase(user)

    fun searchUserRepo(user: String) = searchUserRepoUseCase(user)

}