package com.itl.kglab.composelab

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.itl.kglab.composelab.labItem.LabItem
import com.itl.kglab.composelab.labItem.getTestList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabList(labList = getTestList())
        }
    }
}

// List
@Composable
fun LabList(
    labList: List<LabItem>
) {
    LazyColumn(
    ) {
        itemsIndexed(
            labList
        ) { index, item ->
            Box(
                Modifier.clickable {
                    Log.d("TAG", "LabList: $index - $item")
                }
            ) {
                LabListItem(title = item.title, desc = item.desc)
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}


// ListItem
@Composable
fun LabListItem(
    title: String,
    desc: String
) {
    // 元件ID
    val titleBoxId = "title"
    val descBoxId = "desc"

    // 元件關係定義
    val constraints = ConstraintSet {
        val titleBox = createRefFor(titleBoxId)
        val descBox = createRefFor(descBoxId)

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
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        // Desc Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .layoutId(descBoxId)
                .border(1.dp, Color.Black)
                .padding(vertical = 20.dp, horizontal = 16.dp)
        ) {
            Text(
                text = desc,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }


        // Title Box
        Box(
            modifier = Modifier
                .layoutId(titleBoxId)
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 8.dp)
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .wrapContentWidth()
                    .background(Color.White)
                    .padding(horizontal = 6.dp)
            )
        }
    }
}