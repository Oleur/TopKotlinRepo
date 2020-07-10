package com.js.topkotlinrepo.ui.repogitlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.js.topkotlinrepo.databinding.ItemRepogitBinding
import com.js.topkotlinrepo.ui.common.BindableAdapter
import com.js.topkotlinrepo.ui.common.BindableDiffCallback
import com.js.topkotlinrepo.ui.common.BindableViewHolder
import com.js.topkotlinrepo.ui.common.lifecycle

class RepoGitListAdapter(private val lifecycleOwner: LifecycleOwner) :
    BindableAdapter<RepoGitItemViewModel, RepoGitListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepogitBinding.inflate(inflater).lifecycle(lifecycleOwner).apply {
            root.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        return ViewHolder(binding)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.unbind()
    }

    override fun setData(newList: List<RepoGitItemViewModel>) {
        val diffCallback = BindableDiffCallback(listItems, newList, ::areItemViewModelSame, ::areItemViewModelContentsSame)
        DiffUtil.calculateDiff(diffCallback).dispatchUpdatesTo(this)
        super.setData(newList)
    }

    private fun areItemViewModelSame(old: RepoGitItemViewModel, new: RepoGitItemViewModel): Boolean {
        return old.id == new.id
    }

    private fun areItemViewModelContentsSame(old: RepoGitItemViewModel, new: RepoGitItemViewModel): Boolean {
        return old == new
    }

    inner class ViewHolder(private val binding: ItemRepogitBinding) :
        BindableViewHolder<RepoGitItemViewModel>(binding.root) {
        override fun bind(item: RepoGitItemViewModel) {
            binding.viewModel = item
            binding.executePendingBindings()
        }

        fun unbind() {
            binding.unbind()
        }
    }
}
