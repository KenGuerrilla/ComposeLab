package com.itl.kglab.composelab.screen.passArgsLab

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination

/**
 *  接收args傳遞的參數
 *
 *  支援的傳入參數類型可參考：
 *      - https://composedestinations.rafaelcosta.xyz/destination-arguments/navigation-arguments
 */

@Destination
@Composable
fun PassArgsReceiveScreen(
    inputArgs: InputArgs
) {
    val defaultMsg = "This is a default message because the args message is blank."
    val message = inputArgs.message.ifBlank { defaultMsg }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Text(
                text = "Got the message: $message",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            Text(
                text = "Time is ${inputArgs.time}",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}