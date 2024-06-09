package com.example.hsport


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun mainScreen(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute == BottomNavItem.home.screen_route ||
                currentRoute == BottomNavItem.account.screen_route ||
                currentRoute == BottomNavItem.location.screen_route ||
                currentRoute == BottomNavItem.setting.screen_route
            ) {
                ButtonNav(navController = navController)
            }
        }
    ) {
        NavigationGraph(navController = navController)
    }
}


@Composable
fun ButtonNav(navController: NavController) {
    val list = listOf(
        BottomNavItem.home,
        BottomNavItem.account,
        BottomNavItem.location,
        BottomNavItem.setting
    )
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(69.dp)
            .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)),
        backgroundColor = Color(249,249,251,255), //MaterialTheme.colors.secondary,
        contentColor = Color(244,243,248)//MaterialTheme.colors.onBackground
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        list.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        modifier = Modifier
                            .width(26.84634.dp)
                            .height(24.dp),
                        painter = painterResource(id = item.icon),
                        contentDescription = ""
                    )
                },
                label = {
                    Text(
                        modifier = Modifier.padding(bottom = 25.dp),
                        text = stringResource(id = item.title),
                        style = MaterialTheme.typography.subtitle1,
                        lineHeight = 16.7.sp,
                        fontSize = 11.sp,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Center
                    )
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.onBackground,
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


