package com.js.topkotlinrepo.ui.common

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object RecyclerViewBindingAdapters {

    @JvmStatic
    @BindingAdapter(value = ["setData", "setAdapter"])
    fun <T, VH : BindableViewHolder<T>> RecyclerView.setData(data: List<T>?, adapter: BindableAdapter<T, VH>) {
        if (this.adapter == null) {
            this.adapter = adapter.apply { data?.let { setData(data) } }
        } else {
            @Suppress("UNCHECKED_CAST")
            data?.let { (this.adapter as BindableAdapter<T, VH>).setData(data) }
        }
    }
}
