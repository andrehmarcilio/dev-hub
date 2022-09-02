package br.com.marchiro.devhub.util.extensions

import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import br.com.marchiro.devhub.ui.states.ProfileUiState


fun GitHubProfileDTO.toProfileUiState() : ProfileUiState {
    return ProfileUiState(
        user = this.login,
        name = this.name,
        image = this.img,
        bio = this.bio
    )
}