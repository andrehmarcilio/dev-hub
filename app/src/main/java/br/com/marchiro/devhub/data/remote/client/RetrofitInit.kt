package br.com.marchiro.devhub.data.remote.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInit {

    companion object {
        private lateinit var retrofitClient: Retrofit

        @Synchronized
        fun instance(): Retrofit {
            if(::retrofitClient.isInitialized) return retrofitClient
           return Retrofit.Builder()
               .baseUrl("https://api.github.com/")
               .addConverterFactory(GsonConverterFactory.create())
               .build().also {
                   retrofitClient = it
               }
        }
    }
}