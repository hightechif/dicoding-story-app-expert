package com.fadhil.storyappexpert.util

import android.content.res.Resources
import kotlin.math.roundToInt

object ConvertUtils {

    val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()

}