package br.com.marchiro.devhub

import android.app.Application
import br.com.marchiro.devhub.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyAplicattion : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyAplicattion)
            modules(
                retrofitModule,
                gitHubRemoteDataSourceModule,
                gitHubRepositoryModule,
                useCaseModule,
                viewModelModule
            )

        }

    }
}