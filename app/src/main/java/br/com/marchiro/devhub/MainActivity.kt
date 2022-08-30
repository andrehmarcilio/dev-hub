package br.com.marchiro.devhub

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import br.com.marchiro.devhub.ui.states.State
import br.com.marchiro.devhub.ui.theme.DevHubTheme
import br.com.marchiro.devhub.ui.theme.GitGray
import br.com.marchiro.devhub.ui.viewmodel.MainActivityViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewElements()
    }

    private fun setViewElements() {
        lifecycleScope.launch {
            mainActivityViewModel.getUser()
            mainActivityViewModel.userStateFlow.collect {
                setContent {
                    DevHubTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            when (it) {
                                is State.InitialState, is State.LoadingState -> {
                                    LoadingScreen()
                                }
                                is State.ErrorState -> {
                                    showError()
                                }
                                is State.DataState -> {
                                    ProfileScreen(it.data)
                                }
                            }
                        }
                    }
                }
            }
        }
    }




    private fun showError() {
        Toast.makeText(
            this@MainActivity,
            "Ocorreu algum erro",
            Toast.LENGTH_LONG
        ).show()
    }
}

@Preview
@Composable
fun LoadingScreen() {
    Box(contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            Modifier
                .height(50.dp)
                .width(50.dp)
        )
    }
}

@Preview
@Composable
fun ProfileScreen(
    data: GitHubProfileDTO? = GitHubProfileDTO(
        "Name",
        "https://avatars.githubusercontent.com/u/103319187?v=4",
        "login", "Bio"
    )
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val boxHeight = remember {
            250.dp
        }
        ProfileHeader(boxHeight, data?.img)
        Spacer(Modifier.height(10.dp))
        ProfileInfo(data)
        Spacer(Modifier.height(10.dp))
    }
}


@Composable
private fun ProfileInfo(data: GitHubProfileDTO?) {
    Text(text = data?.name ?: "", fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
    Text(text = data?.login ?: "", fontWeight = FontWeight.ExtraBold)
    Spacer(Modifier.height(10.dp))
    Text(text = data?.bio ?: "")
}

@Composable
private fun ProfileHeader(boxHeight: Dp, img: String?) {
    val viewHeight = boxHeight / 3 * 2
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
                .data(img)
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
}


