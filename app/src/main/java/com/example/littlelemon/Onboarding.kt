package com.example.littlelemon

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Onboarding(navController: NavHostController) {
    val context: Context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }

    var firstNameText by remember { mutableStateOf("") }
    var lastNameText by remember { mutableStateOf("") }
    var emailAddressText by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
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
            label = { Text("First Name") },
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
        Button(onClick = {
            if (firstNameText.isNotBlank() || lastNameText.isNotBlank() || emailAddressText.isNotBlank()) {
                preferencesManager.firstName = firstNameText
                preferencesManager.lastName = lastNameText
                preferencesManager.email = emailAddressText
                navController.navigate(Home.route)
            } else {
                errorText = "Registration unsuccessful. Please enter all data."
            }
            }) {
            Text("Register")
        }
        Text(
            text = errorText,
            color = Color.Red,
        )

    }
}