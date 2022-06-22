package com.itl.kglab.composelab

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_list_screen")
    object TestLabScreen : Screen("nav_args_lab_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
