package com.itl.kglab.composelab.screen.passArgsLab

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.itl.kglab.composelab.screen.passArgsLab.PassArgsScreen.RECEIVE_SCREEN_ROUTE

/**
 *  使用Navigation切換畫面，且使用args傳遞參數。
 *
 *  參考資料：
 *      Navigation -
 *          https://developer.android.com/jetpack/compose/navigation
 *      儲存UI狀態 -
 *          https://developer.android.com/jetpack/compose/state#restore-ui-state
 */

@Composable
fun PassArgsInputScreen(
    navController: NavController
) {
    // 跳轉畫面無需儲存的資料使用remember
    var textFieldState by remember {
        mutableStateOf("")
    }
    // 跳轉畫面需要儲存狀態則使用rememberSaveable
    // 僅使用remember時狀態會在跳轉畫面刷新而遺失
    var timeStampState by rememberSaveable {
        mutableStateOf("尚未紀錄時間")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        TextField(
            value = textFieldState,
            label = {
                Text(text = "請輸入訊息")
            },
            onValueChange = {
                textFieldState = it
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = timeStampState,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            // 由於參數是轉換成URL的方式在傳遞，因此字串不得為空字串，否則會產生路徑解析的問題
            val time = System.currentTimeMillis()
            timeStampState = "上次記錄時間：${time}"
            val args = InputArgs(textFieldState, time)
//            navigateWithSimpleURL(navController = navController, args)
            navigateWithURL(navController, args)
        }) {
            Text(text = "跳轉到下個畫面")
        }
    }
}

// 簡易URL
//  - 參數必須依照順序且不能缺少或空白，否則Navigate的路徑會噴錯誤
private fun navigateWithSimpleURL(navController: NavController, args: InputArgs) {
    val message = args.message.ifBlank { "Message is blank." } // message不能為空白，否則URL會出錯
    navController.navigate("receive_screen/$message/${args.time}")
}

// Optional navigation arguments 格式化URL
//  - 欄位可選的URL，彈性較高且缺少的欄位會使用defaultValue數值替代，因此必須設定defaultValue。
private fun navigateWithURL(navController: NavController, args: InputArgs) {
    // 將參數格式化為URL字串
    val route = buildString {
        append(RECEIVE_SCREEN_ROUTE)
        append("?time=${args.time}")
        if (args.message.isNotBlank()) {append("&message=${args.message}")}
    }
    // 執行跳轉
    navController.navigate(route)
}
