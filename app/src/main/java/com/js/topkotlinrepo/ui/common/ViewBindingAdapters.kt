package com.js.topkotlinrepo.ui.common

import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("enabled")
    fun View.enabled(enabled: Boolean) {
        isEnabled = enabled
    }

    @JvmStatic
    @BindingAdapter("disabled")
    fun View.disabled(disabled: Boolean) {
        isEnabled = !disabled
    }

    @JvmStatic
    @BindingAdapter("activated")
    fun View.activated(activated: Boolean) {
        isActivated = activated
    }

    @JvmStatic
    @BindingAdapter("deactivated")
    fun View.deactivated(deactivated: Boolean) {
        isActivated = !deactivated
    }

    @JvmStatic
    @BindingAdapter("visible")
    fun View.visible(visible: Boolean) {
        isVisible = visible
    }

    @JvmStatic
    @BindingAdapter("invisible")
    fun View.invisible(invisible: Boolean) {
        isInvisible = invisible
    }

    @JvmStatic
    @BindingAdapter("gone")
    fun View.gone(gone: Boolean) {
        isGone = gone
    }

    @JvmStatic
    @BindingAdapter("onLongClick")
    fun View.setOnLongClick(block: () -> Unit) {
        setOnLongClickListener {
            block()
            true
        }
    }

    @JvmStatic
    @BindingAdapter("backgroundColorRes")
    fun View.setBackgroundColorRes(@ColorRes colorRes: Int) {
        if (colorRes != 0) {
            setBackgroundColor(ResourcesCompat.getColor(resources, colorRes, context.theme))
        }
    }

    @JvmStatic
    @BindingAdapter("selected")
    fun View.selected(selected: Boolean) {
        isSelected = selected
    }

    @JvmStatic
    @BindingAdapter("deselected")
    fun View.deselected(deselected: Boolean) {
        isSelected = !deselected
    }
}
