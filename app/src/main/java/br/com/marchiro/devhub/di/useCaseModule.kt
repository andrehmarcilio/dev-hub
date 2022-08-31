package br.com.marchiro.devhub.di

import br.com.marchiro.devhub.domain.usecases.GetUserInfoUseCase
import br.com.marchiro.devhub.domain.usecases.ReadUserInfoUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetUserInfoUseCase(get()) }
    factory { ReadUserInfoUseCase(get()) }
}