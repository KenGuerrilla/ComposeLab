package com.itl.kglab.composelab

sealed class LabsScreen(val route: String) {
    object MainScreen : LabsScreen("main_list_screen")
    object NavArgsLabScreen : LabsScreen("nav_pass_agrs_lab")
    object LayoutBasicLabScreen : LabsScreen("nav_layout_basic_lab")
}
