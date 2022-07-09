package com.itl.kglab.composelab.screen.layoutLab

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination

/**
 *
 * 示範Compose基本的排版Layout
 *
 * Note.
 *  A. 要特別注意！！！Modifier的參數設定有順序性，例如Padding與Border順序對調就會有差異
 *  B. 基於A點，scroll的設定必須要在Layout大小之後，例如height，否則會沒有效果
 *
 *  參考文件：
 *      https://developer.android.com/jetpack/compose/layouts/basics
 *      https://developer.android.com/jetpack/compose/lists
 *      https://developer.android.com/jetpack/compose/gestures
 */

@Destination
@Composable
fun LayoutBasicLabScreen() {
    val columnScrollState = rememberScrollState()
    val rowScrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize() // 高與寬填滿Parent
    ) {

        // Column排版，可以作為簡易List使用，但需要產生大量Item建議使用LazyColumn。
        Column(
            modifier = Modifier
                .fillMaxWidth() // 寬填滿Parent
//                .verticalScroll(columnScrollState) // 垂直捲動，注意！！放在這裡height前面不會有作用。
                .height(100.dp)
                .background(Color.LightGray)
                .padding(8.dp)
                .verticalScroll(columnScrollState) // 垂直捲動
        ) {
            repeat(10) {
                DemoText(
                    text = "我是Column Text$it",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .height(8.dp)
        )

        // Row排版，可作為簡易List使用，但需要產生大量Item建議使用LazyRow。
        Row(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(8.dp)
                .horizontalScroll(rowScrollState)
                .fillMaxWidth()
        ) {
            repeat(10) {
                DemoText(
                    text = "我是Row Text$it"
                )
            }
        }

        Spacer(
            modifier = Modifier
                .height(8.dp)
        )

        // Box排版，用來處理堆疊層次的Layout
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .height(128.dp)
                .fillMaxWidth()
        ) {
            DemoText(
                text = "我是Box的主要Text",
                modifier = Modifier
                    .align(Alignment.Center)
            )

            DemoText(
                text = "我是Box的小小Text",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = (-8).dp, y = (-8).dp)
            )
        }

    }
}

// 可以將重複的屬性提出成一個獨立的Composable
@Composable
private fun DemoText(
    text: String,
    modifier: Modifier = Modifier // 由外部傳入Modifier，再由內部做額外的定義
) {
    Text(
        text = text,
        modifier = modifier
            .border(1.dp, Color.Black)
            .padding(8.dp)
    )
}
