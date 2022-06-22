package com.itl.kglab.composelab

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.itl.kglab.composelab.labItem.getTestList
import com.itl.kglab.composelab.screen.MainLabListScreen
import com.itl.kglab.composelab.screen.NavArgsLabScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route ) {
        composable(route = Screen.MainScreen.route) {
            MainLabListScreen(navController = navController, labList = getTestList())
        }
        composable(
            route = Screen.TestLabScreen.route + "/{desc}/{time}",
            arguments = listOf(
                navArgument("desc") {
                    type = NavType.StringType
                    defaultValue = "This is arg."
                    nullable = true
                },
                navArgument("time") {
                    type = NavType.LongType
                    defaultValue = 0L
//                    nullable = true <<< 這個要特別注意，Long不支援Nullable，加上去會噴錯誤
                }
            )
        ) { entry ->
            NavArgsLabScreen(
                navController = navController,
                desc = entry.arguments?.getString("desc"),
                time = entry.arguments?.getLong("time")
            )
        }
    }
}

