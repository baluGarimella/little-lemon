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
fun Profile(navController: NavHostController) {
    val context: Context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    Column {
        Image(modifier = Modifier.size(300.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Cartoon of dog")
        Text(
            modifier = Modifier.padding(20.dp),
            text = "Personal Information",
            fontSize = 24.sp)
        Text(
            text = preferencesManager.firstName
        )
        Text(
            text = preferencesManager.lastName
        )
        Text(
            text = preferencesManager.email
        )
        Button(onClick = {
            preferencesManager.firstName = ""
            preferencesManager.lastName = ""
            preferencesManager.email = ""
            navController.navigate(Onboarding.route)
        }) {
            Text("LogOut")
        }
    }
}