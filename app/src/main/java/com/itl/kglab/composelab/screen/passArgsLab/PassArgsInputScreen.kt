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
import com.itl.kglab.composelab.screen.destinations.PassArgsReceiveScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 *
 *  使用Compose Destination傳遞Args範例
 *
 *  如果有Compose Navigation傳遞Args的需求，可參考官方文件，專案實作Demo可參考先前的Commit
 *   - https://developer.android.com/jetpack/compose/navigation
 *
 *  儲存UI狀態
 *   - https://developer.android.com/jetpack/compose/state#restore-ui-state
 *
 */

@Destination
@Composable
fun PassArgsInputScreen(
    navigator: DestinationsNavigator,
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
            navigator.navigate(
                PassArgsReceiveScreenDestination(
                    args
                )
            )
        }) {
            Text(text = "跳轉到下個畫面")
        }
    }
}
