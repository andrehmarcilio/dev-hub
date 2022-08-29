package br.com.marchiro.devhub

import android.os.Bundle
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
import br.com.marchiro.devhub.ui.theme.DevHubTheme
import br.com.marchiro.devhub.ui.theme.GitGray
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

@Preview
@Composable
fun ProfileScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val boxHeight = remember {
            250.dp
        }
        ProfileHeader(boxHeight)
        Spacer(Modifier.height(10.dp))
        ProfileInfo()
        Spacer(Modifier.height(10.dp))
    }
}


@Composable
private fun ProfileInfo() {
    Text(text = "André Marcílio", fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
    Text(text = "andrehmarcilio", fontWeight = FontWeight.ExtraBold)
    Spacer(Modifier.height(10.dp))
    Text(text = "Desenvolvedor Mobile Junior (Flutter & Kotlin)")
}

@Composable
private fun ProfileHeader(boxHeight: Dp) {
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
                .data("https://avatars.githubusercontent.com/u/103319187?v=4")
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


