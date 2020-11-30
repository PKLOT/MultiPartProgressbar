package com.pklotcorp.multipartprogressbar.util

import android.content.res.Resources

internal val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()