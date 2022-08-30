package br.com.marchiro.devhub.di

import br.com.marchiro.devhub.data.repository.GitHubRepositoryImpl
import br.com.marchiro.devhub.domain.repository.GitHubRepository
import org.koin.dsl.module

val gitHubRepositoryModule = module {
    single { GitHubRepositoryImpl(get()) as GitHubRepository }
}