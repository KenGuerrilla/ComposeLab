package com.itl.kglab.composelab

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itl.kglab.composelab.labItem.getLabsList
import com.itl.kglab.composelab.screen.MainLabListScreen
import com.itl.kglab.composelab.screen.passArgsLab.passArgsGraph

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = LabsScreen.MainScreen.route ) {

        composable(route = LabsScreen.MainScreen.route) {
            MainLabListScreen(navController = navController, labList = getLabsList())
        }

        // 載入Nested Navigation
        passArgsGraph(navController)
    }
}

