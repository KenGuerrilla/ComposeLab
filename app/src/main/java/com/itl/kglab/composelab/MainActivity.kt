package com.itl.kglab.composelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.itl.kglab.composelab.screen.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

/**
 *
 *  專案使用Compose Destination來簡化原本Compose Navigation小複雜的操作方式
 *
 *  A. 僅需要在ComposeScreen標上@Destination
 *  B. Screen引數即為需要傳入之參數
 *  C. 需要在setContent設定DestinationsNavHost
 *
 *
 * --- 參考資料 ---
 *
 *  Github
 *   - https://github.com/raamcosta/compose-destinations
 *
 *  Documentation website
 *   - https://composedestinations.rafaelcosta.xyz/
 *
 *  Compose Navigation Just Got SO MUCH EASIER -- Philipp Lackner
 *   - https://youtu.be/Q3iZyW2etm4
 *
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 設定NavHost
            DestinationsNavHost(
                navGraph = NavGraphs.root
            )
        }
    }
}
