package br.com.marchiro.devhub.ui.screen

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.marchiro.devhub.ui.activity.UserInfoActivity
import br.com.marchiro.devhub.ui.theme.GitGray
import br.com.marchiro.devhub.ui.viewmodel.UserFormViewModel
import org.koin.androidx.compose.getViewModel

@Preview(showBackground = true)
@Composable
fun formView(userFormViewModel: UserFormViewModel = getViewModel()) {
    val context = LocalContext.current

    val text = remember { mutableStateOf("") }
    Box(
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "DevHub", fontSize = 38.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), value = text.value,
                onValueChange = {
                    text.value = it
                },
                label = { Text("user") })
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    userFormViewModel.searchUserInfo(text.value)
                    userFormViewModel.searchUserRepo(text.value)
                    Intent(context, UserInfoActivity::class.java).let {
                        context.startActivity(it)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = GitGray)
            ) {
                Text("Entrar", color = Color.White)
            }

        }
    }
}