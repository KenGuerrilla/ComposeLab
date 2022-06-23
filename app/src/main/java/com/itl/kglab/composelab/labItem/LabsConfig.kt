package com.itl.kglab.composelab.labItem

import com.itl.kglab.composelab.LabsScreen

/**
 *  Labs定義
 */

fun getLabsList(): List<LabItem> = mutableListOf(
    LabItem(
        "Navigation Args Lab",
        "Pass args between destinations.",
        LabsScreen.NavArgsLabScreen,
    ),
)

data class LabItem (
    val title: String = "Title Empty",
    val desc: String = "Desc Empty",
    val labsScreen: LabsScreen,
)