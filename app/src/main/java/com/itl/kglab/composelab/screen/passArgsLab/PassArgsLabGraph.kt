package com.itl.kglab.composelab.screen.passArgsLab

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.itl.kglab.composelab.screen.passArgsLab.PassArgsScreen.ARG_MESSAGE
import com.itl.kglab.composelab.screen.passArgsLab.PassArgsScreen.ARG_TIME
import com.itl.kglab.composelab.screen.passArgsLab.PassArgsScreen.INPUT_SCREEN_ROUTE
import com.itl.kglab.composelab.screen.passArgsLab.PassArgsScreen.RECEIVE_SCREEN_ROUTE

/**
 *  定義Nested Navigation
 *
 *  參考資料：
 *      https://developer.android.com/jetpack/compose/navigation#nested-nav
 *      https://developer.android.com/guide/navigation/navigation-pass-data#supported_argument_types
 */
object PassArgsScreen {
    const val INPUT_SCREEN_ROUTE = "input_screen"
    const val RECEIVE_SCREEN_ROUTE = "receive_screen"

    const val ARG_MESSAGE = "message"
    const val ARG_TIME = "time"
}

fun NavGraphBuilder.passArgsGraph(navController: NavController) {
    val navArgs = "nav_pass_agrs_lab"

    navigation(startDestination = INPUT_SCREEN_ROUTE, route = navArgs) {
        composable(INPUT_SCREEN_ROUTE) {
            PassArgsInputScreen(navController = navController)
        }
        composable(
            // 參數的部分記得要用{}包起來
            // 簡易URL格式，缺點是參數必須依照順序且不能缺少或空白，否則Navigate的路徑會噴錯誤
//            route = "$RECEIVE_SCREEN_ROUTE/{$ARG_MESSAGE}/{$ARG_TIME}",
            // 完整的URL，彈性較高且缺少的欄位會使用defaultValue數值替代，因此必須設定defaultValue。
            route = "$RECEIVE_SCREEN_ROUTE?message={message}&time={time}",
            arguments = listOf(
                navArgument(ARG_MESSAGE) {
                    type = NavType.StringType
                    defaultValue = "This is navArgument default message."
                    nullable = true
                },
                navArgument(ARG_TIME) {
                    type = NavType.LongType
                    defaultValue = 0L
//                    nullable = true // <<< 這個要特別注意，Long不支援Nullable，加上去會噴錯誤
                }
            )
        ) { entry ->
            PassArgsReceiveScreen(
                navController = navController,
                message = entry.arguments?.getString(ARG_MESSAGE),
                time = entry.arguments?.getLong(ARG_TIME)
            )
        }
    }
}