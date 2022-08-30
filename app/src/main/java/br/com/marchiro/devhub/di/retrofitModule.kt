package br.com.marchiro.devhub.di

import br.com.marchiro.devhub.data.remote.client.RetrofitInit
import org.koin.dsl.module

val retrofitModule = module {
    single { RetrofitInit.instance() }
}