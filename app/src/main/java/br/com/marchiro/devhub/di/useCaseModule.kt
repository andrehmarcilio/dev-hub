package br.com.marchiro.devhub.di

import br.com.marchiro.devhub.domain.usecases.SearchUserInfoUseCase
import br.com.marchiro.devhub.domain.usecases.ReadUserInfoUseCase
import br.com.marchiro.devhub.domain.usecases.ReadUserRepoUseCase
import br.com.marchiro.devhub.domain.usecases.SearchUserRepoUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { SearchUserInfoUseCase(get()) }
    factory { ReadUserInfoUseCase(get()) }
    factory { SearchUserRepoUseCase(get()) }
    factory { ReadUserRepoUseCase(get()) }
}