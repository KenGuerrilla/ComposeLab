package com.itl.kglab.composelab.labItem

fun getTestList(): List<LabItem> {
    val list = mutableListOf<LabItem>()

    for (index in 0..100) {
        list.add(LabItem("Lab No.$index", "This is $index lab desc"))
    }

    return list
}