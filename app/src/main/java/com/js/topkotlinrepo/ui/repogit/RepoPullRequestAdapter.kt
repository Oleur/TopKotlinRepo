package com.js.topkotlinrepo.ui.repogit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.js.topkotlinrepo.databinding.ItemRepoPullRequestBinding
import com.js.topkotlinrepo.ui.common.BindableAdapter
import com.js.topkotlinrepo.ui.common.BindableDiffCallback
import com.js.topkotlinrepo.ui.common.BindableViewHolder
import com.js.topkotlinrepo.ui.common.lifecycle

class RepoPullRequestAdapter(private val lifecycleOwner: LifecycleOwner) :
    BindableAdapter<RepoPullRequestViewModel, RepoPullRequestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepoPullRequestBinding.inflate(inflater).lifecycle(lifecycleOwner).apply {
            root.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        return ViewHolder(binding)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.unbind()
    }

    override fun setData(newList: List<RepoPullRequestViewModel>) {
        val diffCallback = BindableDiffCallback(listItems, newList, ::areItemViewModelSame, ::areItemViewModelContentsSame)
        DiffUtil.calculateDiff(diffCallback).dispatchUpdatesTo(this)
        super.setData(newList)
    }

    private fun areItemViewModelSame(old: RepoPullRequestViewModel, new: RepoPullRequestViewModel): Boolean {
        return old.number == new.number
    }

    private fun areItemViewModelContentsSame(old: RepoPullRequestViewModel, new: RepoPullRequestViewModel): Boolean {
        return old == new
    }

    inner class ViewHolder(private val binding: ItemRepoPullRequestBinding) :
        BindableViewHolder<RepoPullRequestViewModel>(binding.root) {
        override fun bind(item: RepoPullRequestViewModel) {
            binding.viewModel = item
            binding.executePendingBindings()
        }

        fun unbind() {
            binding.unbind()
        }
    }
}
