import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.key.shortcuts
import src.main.java.hashing
import crypt

import src.main.java.databaseScripts.connectToDatatabase


@ExperimentalKeyInput
fun main() = Window(title = "Ostrowiskia", size = IntSize(300, 300)) {
    MaterialTheme {
        var userInputPassword by remember { mutableStateOf(" ")}
        var text by remember { mutableStateOf("") }
        Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
            Text("Enter Password")
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.shortcuts {
                    on(Key.Enter) {
                        userInputPassword = text
                        if (userInputPassword.isNotBlank()){
                            val hashedPassword = hashing.main(userInputPassword)
                            connectToDatatabase()
                        }
                        text = ""


                    }
                }
            )
        }
    }

}