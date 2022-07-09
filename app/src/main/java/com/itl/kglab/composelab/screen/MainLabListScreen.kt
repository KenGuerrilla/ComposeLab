package com.itl.kglab.composelab.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.itl.kglab.composelab.labItem.getLabsList
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 *
 *  MainLabList
 *
 *  Note
 *   A. 考量到Title需要做寬度限制，因此使用ConstraintLayout作為ListItem基底。
 *
 */

// 設定為起始畫面
@RootNavGraph(start = true)
@Destination
@Composable
fun MainLabListScreen(
    navigator: DestinationsNavigator // 改為使用DestinationsNavigator
) {
    val labList = getLabsList()
    LazyColumn {
        itemsIndexed(
            labList
        ) { index, item ->
            LabListItem(
                title = "${index + 1} - ${item.title}",
                desc = item.desc,
                modifier = Modifier
                    .clickable {
                        navigator.navigate(item.destination)
                    }
            )
            // List邊界分切線，
            Divider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}

// ListItem
@Composable
private fun LabListItem(
    title: String,
    desc: String,
    modifier: Modifier = Modifier
) {
    // 元件ID
    val titleId = "title"
    val descId = "desc"

    // 元件關係定義
    val constraints = ConstraintSet {
        val titleBox = createRefFor(titleId)
        val descBox = createRefFor(descId)

        constrain(titleBox) {
            top.linkTo(descBox.top)
            bottom.linkTo(descBox.top)
            start.linkTo(parent.start, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
            width = Dimension.percent(0.9f) // 元件寬度用百分比做限制
        }

        constrain(descBox) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start, margin = 8.dp)
            end.linkTo(parent.end, margin = 8.dp)
            width = Dimension.percent(0.9f) // 元件寬度用百分比做限制
        }
    }

    // Layout
    ConstraintLayout(
        constraints,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        // Desc文字
        Text(
            text = desc,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis, // 當文字超過螢幕的處理方式
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .layoutId(descId)
                .border(1.dp, Color.Black)
                .padding(vertical = 20.dp, horizontal = 16.dp)
        )

        // 為了讓Title能夠Match上層寬度且靠左，因此用一個Box包覆
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .layoutId(titleId)
        ) {
            // Title則依照內容做寬度調整
            Text(
                text = title,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis, // 當文字超過螢幕的處理方式
                modifier = Modifier
                    .wrapContentWidth()
                    .background(Color.White)
                    .padding(horizontal = 6.dp)
            )
        }
    }
}