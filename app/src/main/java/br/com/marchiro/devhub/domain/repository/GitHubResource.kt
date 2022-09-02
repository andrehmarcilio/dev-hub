package br.com.marchiro.devhub.domain.repository

class GitHubResource<T>(val data: T?, val erro: String? = null, val loading: Boolean = false)
