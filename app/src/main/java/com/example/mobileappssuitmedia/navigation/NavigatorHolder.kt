package com.example.mobileappssuitmedia.navigation

sealed class NavigatorHolder(val route: String) {
    object LoginScreen : NavigatorHolder("main_screen")
    object SecondScreen : NavigatorHolder("second_screen")
    object ThirdScreen : NavigatorHolder("third_screen")
}
