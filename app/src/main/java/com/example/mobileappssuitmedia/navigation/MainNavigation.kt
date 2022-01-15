package com.example.mobileappssuitmedia.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobileappssuitmedia.presentation.login.LoginScreen
import com.example.mobileappssuitmedia.presentation.second.SecondScreen
import com.example.mobileappssuitmedia.presentation.third.ThirdScreen
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val uiController = rememberSystemUiController()
    ProvideWindowInsets {
        SideEffect {
            uiController.setSystemBarsColor(Color.Transparent, darkIcons = true)
        }
        NavHost(
            navController = navController,
            startDestination = NavigatorHolder.LoginScreen.route
        ) {
            composable(NavigatorHolder.LoginScreen.route) {
                LoginScreen(nav = navController)
            }
            composable(
                "${NavigatorHolder.SecondScreen.route}/{userName}", arguments = listOf(
                    navArgument("userName") {
                        type = NavType.StringType
                    })
            ) { navStack ->
                SecondScreen(
                    nav = navController,
                    username = navStack.arguments?.getString("userName") ?: "Unknown"
                )
            }
            composable(NavigatorHolder.ThirdScreen.route) {
                ThirdScreen(nav = navController)
            }
        }
    }
}