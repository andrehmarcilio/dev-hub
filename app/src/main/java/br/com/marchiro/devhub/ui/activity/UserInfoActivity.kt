package br.com.marchiro.devhub.ui.activity

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.marchiro.devhub.R
import br.com.marchiro.devhub.data.remote.dto.GitHubProfileDTO
import br.com.marchiro.devhub.ui.states.State
import br.com.marchiro.devhub.ui.theme.DevHubTheme
import br.com.marchiro.devhub.ui.theme.GitGray
import br.com.marchiro.devhub.ui.viewmodel.UserInfoViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import org.koin.androidx.compose.getViewModel


class UserInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewElements()
    }

    private fun setViewElements() {
        setContent {
            DevHubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(viewModel: UserInfoViewModel = getViewModel()) {
    val findData by viewModel.userStateFlow.collectAsState()
    findData.let {
        when (it) {
            is State.InitialState, is State.LoadingState -> {
                LoadingScreen()
            }
            is State.ErrorState -> {
                showError(LocalContext.current)
                errorScreen()
            }
            is State.DataState -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val boxHeight = remember {
                        250.dp
                    }
                    ProfileHeader(boxHeight, it.data?.img)
                    Spacer(Modifier.height(10.dp))
                    ProfileInfo(it.data)
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }

}

@Preview
@Composable
fun errorScreen() {
    val context = LocalContext.current
    Box(contentAlignment = Alignment.Center) {
        Button(onClick = {
            context.findActivity().finish()
        },
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(color = GitGray)) {
            Text(text = "Voltar", color = Color.White)
        }

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

fun showError(context: Context) {
    Toast.makeText(
        context,
        "Ocorreu algum erro",
        Toast.LENGTH_LONG
    ).show()
}


fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}