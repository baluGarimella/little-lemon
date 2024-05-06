package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Nav(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Onboarding"){
       composable(route = "Onboarding"){
           Onboarding(navController)
       }
        composable(route = "Home"){
            Home(navController)
        }
        composable(route = "Profile"){
            Profile(navController)
        }
    }
}