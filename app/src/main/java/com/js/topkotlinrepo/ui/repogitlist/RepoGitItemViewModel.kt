package com.js.topkotlinrepo.ui.repogitlist

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
