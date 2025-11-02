package com.openkeyboard.myapplication.ui.navigation

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.openkeyboard.myapplication.SettingsScreen
import com.openkeyboard.myapplication.prefs.PrefsHelper
import com.openkeyboard.myapplication.ui.DetailsScreen
import com.openkeyboard.myapplication.ui.OnboardingScreen
import com.openkeyboard.myapplication.ui.home.HomeScreen

@Composable
fun AppNavigation() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val startDestination = if (PrefsHelper.isOnBoardingComplete(context)) {
        Routes.Home
    } else {
        Routes.OnBoardScreen
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(Routes.OnBoardScreen) {
            OnboardingScreen(navController = navController,
                onFinish = {
                    PrefsHelper.setOnBoardingComplete(context, true)
                    navController.navigate(Routes.Home) {
                        // Clear onboarding from back stack
                        popUpTo(Routes.OnBoardScreen) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Home) {
            HomeScreen(modifier = Modifier.padding(0.dp), navController = navController)
        }
        composable(Routes.DetailsScreen){ DetailsScreen(modifier = Modifier.padding(0.dp),navController = navController) }
        composable(Routes.SettingScreen){ SettingsScreen(
            navController = navController,
            onBackClicked = {}
        ) }
    }
}