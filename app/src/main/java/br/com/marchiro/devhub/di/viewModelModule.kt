package br.com.marchiro.devhub.di

import br.com.marchiro.devhub.ui.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainActivityViewModel(get()) }
}