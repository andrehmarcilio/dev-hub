package br.com.marchiro.devhub.ui.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.marchiro.devhub.ui.theme.GitGray
import br.com.marchiro.devhub.util.extensions.findActivity

@Preview(showBackground = true)
@Composable
fun errorScreen() {
    val context = LocalContext.current
    Box(contentAlignment = Alignment.Center) {
        Button(
            onClick = {
                context.findActivity().finish()
            },
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(color = GitGray)
        ) {
            Text(text = "Voltar", color = Color.White)
        }

    }
}