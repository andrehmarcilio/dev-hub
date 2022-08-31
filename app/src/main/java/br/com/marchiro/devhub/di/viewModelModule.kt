package br.com.marchiro.devhub.di

import br.com.marchiro.devhub.ui.viewmodel.UserInfoViewModel
import br.com.marchiro.devhub.ui.viewmodel.UserFormViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserInfoViewModel(get()) }
    viewModel { UserFormViewModel(get()) }
}