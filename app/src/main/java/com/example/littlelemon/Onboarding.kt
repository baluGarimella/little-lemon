package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Onboarding(navController: NavHostController) {
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