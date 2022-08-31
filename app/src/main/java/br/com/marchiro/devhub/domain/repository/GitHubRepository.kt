package br.com.marchiro.devhub.domain.repository

import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import br.com.marchiro.devhub.ui.states.State
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    fun getUserInfo(user: String) : Flow<State<GitHubProfileDTO?>>
    fun readUserInfo() : Flow<State<GitHubProfileDTO?>>
}