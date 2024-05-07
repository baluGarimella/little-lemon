package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = Onboarding.route
    ) {
        composable(Home.route) {
            Home(navController = navController)
        }
        composable(Profile.route) {
            Profile(navController = navController)
        }
        composable(Onboarding.route) {
            Onboarding(navController = navController)
        }
    }

}