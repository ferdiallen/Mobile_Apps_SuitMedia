package com.example.mobileappssuitmedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.mobileappssuitmedia.navigation.MainNavigation
import com.example.mobileappssuitmedia.ui.theme.MobileAppsSuitMediaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MobileAppsSuitMediaTheme {
                MainNavigation()
            }
        }
    }
}

