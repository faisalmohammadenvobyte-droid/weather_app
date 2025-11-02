package com.openkeyboard.myapplication.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.openkeyboard.myapplication.ui.OnboardingScreen
import com.openkeyboard.myapplication.ui.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "onBoard"
    ) {
        composable("onBoard") { OnboardingScreen(navController) }
        composable("home") { HomeScreen(navController = navController, modifier = Modifier.padding(0.dp)) }

    }
}