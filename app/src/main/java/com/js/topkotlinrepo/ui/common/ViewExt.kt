package com.js.topkotlinrepo.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun <T : ViewDataBinding> T.lifecycle(lifecycleOwner: LifecycleOwner): T = apply { this.lifecycleOwner = lifecycleOwner }
