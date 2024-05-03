package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.LittlelemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittlelemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var firstNameText by remember { mutableStateOf("first name") }
    var lastNameText by remember { mutableStateOf("last name") }
    var emailAddressText by remember { mutableStateOf("email address") }
    Column {
        Image(modifier = Modifier.size(300.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Cartoon of dog")
        Text(
            style = TextStyle(background = Color.Yellow),
            modifier = Modifier.padding(20.dp),
            text = "Let's get to know you",
            fontSize = 24.sp)
        TextField(
            value = firstNameText,
            onValueChange = { firstNameText = it },
            label = { Text("First Name") }
        )
        TextField(
            value = lastNameText,
            onValueChange = { lastNameText = it },
            label = { Text("Last Name") }
        )
        TextField(
            value = emailAddressText,
            onValueChange = { emailAddressText = it },
            label = { Text("Email") }
        )
        Button(onClick = {  }) {
            Text("Register")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LittlelemonTheme {
        Greeting("Android")
    }
}