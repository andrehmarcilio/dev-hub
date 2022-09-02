package br.com.marchiro.devhub.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.marchiro.devhub.R
import br.com.marchiro.devhub.data.remote.dto.GitHubRepositoryDTO
import br.com.marchiro.devhub.ui.screen.component.RepositoryItem
import br.com.marchiro.devhub.ui.screen.component.errorScreen
import br.com.marchiro.devhub.ui.states.ProfileUiState
import br.com.marchiro.devhub.ui.states.State
import br.com.marchiro.devhub.ui.theme.GitGray
import br.com.marchiro.devhub.ui.viewmodel.UserInfoViewModel
import br.com.marchiro.devhub.util.extensions.showError
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import org.koin.androidx.compose.getViewModel

@Composable
fun ProfileScreen(viewModel: UserInfoViewModel = getViewModel()) {
    val uiState = viewModel.userStateFlow

    uiState.let {
        when (it) {
            is State.InitialState, is State.LoadingState -> {
                LoadingScreen()
            }
            is State.ErrorState -> {
                LocalContext.current.showError()
                errorScreen()
            }
            is State.DataState -> {
                Profile(it.data)
            }
        }
    }

}


@Composable
fun Profile(data: ProfileUiState?) {
    val boxHeight = remember {
        250.dp
    }
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            ProfileHeader(boxHeight, data)
        }
        if (data?.repositories != null && data.repositories.isNotEmpty()) {
            item {
                Text(
                    text = "Repositórios",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(Modifier.height(10.dp))
            }
            items(data.repositories) { repo ->
                RepositoryItem(repo)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(
        data = ProfileUiState(
            user = "andrehmarcilio",
            image = "https://avatars.githubusercontent.com/u/103319187?s=96&v=4",
            name = "André Marcílio",
            bio = "Desenvolvedor Mobile Jr lalalalalalallalallalalalall aksdaksd adkadsan asndsa",
            repositories = listOf(
                GitHubRepositoryDTO(
                    name = "AndréMarcílio",
                    description = "Meu Repositório"
                )
            )
        )
    )
}


@Composable
private fun ProfileInfo(data: ProfileUiState?) {
    Text(
        text = data?.name ?: "",
        fontSize = 28.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Text(
        text = data?.user ?: "",
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(Modifier.height(10.dp))
    Text(
        text = data?.bio ?: "",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
}

@Composable
private fun ProfileHeader(boxHeight: Dp, data: ProfileUiState?) {
    val viewHeight = boxHeight / 3 * 2
    Column {
        Box(
            Modifier
                .height(boxHeight)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomStart
        ) {
            Card(
                Modifier
                    .fillMaxWidth()
                    .height(viewHeight)
                    .align(Alignment.TopCenter),
                shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp),
                backgroundColor = GitGray,

                ) {

            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data?.image)
                    .crossfade(true)
                    .scale(Scale.FILL)
                    .build(),
                contentDescription = "Foto perfil",
                modifier = Modifier
                    .height(viewHeight)
                    .width(viewHeight)
                    .align(Alignment.BottomCenter)
                    .clip(CircleShape),
                placeholder = painterResource(R.drawable.placeholder)
            )
        }
        Spacer(Modifier.height(10.dp))
        ProfileInfo(data)
        Spacer(Modifier.height(10.dp))
    }

}

