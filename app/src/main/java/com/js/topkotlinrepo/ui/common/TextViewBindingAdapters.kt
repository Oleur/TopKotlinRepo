package com.js.topkotlinrepo.ui.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import kotlin.math.ln
import kotlin.math.pow

object TextViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("prettyNumberText")
    fun TextView.compactDecimalText(number: Long) {
        text = getFormattedNumber(number)
    }

    private fun getFormattedNumber(number: Long): String {
        if (number < 1000) return number.toString()
        val exp = (ln(number.toDouble()) / ln(1000.0)).toInt()
        return String.format("%.1f %c", number / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
    }
}
