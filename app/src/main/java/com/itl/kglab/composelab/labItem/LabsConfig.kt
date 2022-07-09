package com.itl.kglab.composelab.labItem

import com.itl.kglab.composelab.screen.destinations.DirectionDestination
import com.itl.kglab.composelab.screen.destinations.LayoutBasicLabScreenDestination
import com.itl.kglab.composelab.screen.destinations.PassArgsInputScreenDestination

/**
 *  Labs定義
 */

fun getLabsList(): List<LabItem> = mutableListOf(
    LabItem(
        "Navigation Args Lab",
        "Pass args between destinations.",
        PassArgsInputScreenDestination
    ),
    LabItem(
        "Layout Basic Lab",
        "Column, Row and Box layout demo.",
       LayoutBasicLabScreenDestination
    )
)

data class LabItem (
    val title: String = "Title Empty",
    val desc: String = "Desc Empty",
    val destination: DirectionDestination,
)