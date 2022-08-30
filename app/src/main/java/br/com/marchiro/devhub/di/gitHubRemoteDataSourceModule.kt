package br.com.marchiro.devhub.di

import br.com.marchiro.devhub.data.remote.client.ServiceInit
import org.koin.dsl.module

val gitHubRemoteDataSourceModule = module {
    single { ServiceInit.instance(get()) }
}