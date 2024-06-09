package com.example.hsport


import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kharidino.R


sealed class BottomNavItem(@SuppressLint("SupportAnnotationUsage") @StringRes var title: Int, var icon: Int, var screen_route: String) {
    object home : BottomNavItem(R.string.home, R.drawable.home, "home")
    object account :
        BottomNavItem(R.string.account, R.drawable.account, "account")

    object location : BottomNavItem(R.string.location, R.drawable.location, "location")
    object setting : BottomNavItem(R.string.setting, R.drawable.setting, "setting")
}

sealed class NavigationItems(var screen_route: String) {
    object home : NavigationItems("home")
    object account : NavigationItems("account")
    object location : NavigationItems("location")
    object setting : NavigationItems("setting")
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.home.screen_route) {
        composable(BottomNavItem.home.screen_route) {
            home(navController)
        }
        composable(BottomNavItem.account.screen_route) {
            account(navController)
        }
        composable(BottomNavItem.location.screen_route) {
            location(navController)
        }
        composable(BottomNavItem.setting.screen_route) {
            setting(navController)
        }
        composable("notification") {
            notification(navController)
        }
    }
}






