package com.itl.kglab.composelab.screen.passArgsLab

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 *  定義Args data class
 *
 *  注意！Compose Destination的Args其Data class需要用Parcelable形式來處理
 */

@Parcelize
data class InputArgs(
    val message: String,
    val time: Long
): Parcelable
