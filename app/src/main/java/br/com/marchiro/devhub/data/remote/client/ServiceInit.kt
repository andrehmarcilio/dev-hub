package br.com.marchiro.devhub.data.remote.client

import br.com.marchiro.devhub.data.remote.dataSource.GitHubRemoteDataSource
import retrofit2.Retrofit


class ServiceInit {
    companion object {
        private lateinit var gitHubRemoteDataSource: GitHubRemoteDataSource

        @Synchronized
        fun instance(client: Retrofit): GitHubRemoteDataSource {
            if(::gitHubRemoteDataSource.isInitialized) return gitHubRemoteDataSource
            return client.create(GitHubRemoteDataSource::class.java).also {
                gitHubRemoteDataSource = it
            }
        }
    }
}