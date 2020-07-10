package com.js.topkotlinrepo.ui.repogitlist

import com.js.topkotlinrepo.domain.repogitlist.RepoGit

data class RepoGitItemViewModel(
    val id: String,
    val name: String,
    val owner: String,
    val description: String?,
    val stars: Int,
    val onRepoGitSelected: (RepoGitItemViewModel) -> Unit
) {

    fun onRepoGitSelected() {
        onRepoGitSelected(this)
    }
}

fun RepoGit.toRepoGitItemViewModel(onRepoGitSelected: (RepoGitItemViewModel) -> Unit): RepoGitItemViewModel {
    return RepoGitItemViewModel(id, name, owner, description, stars, onRepoGitSelected)
}
