package com.js.topkotlinrepo.ui.repogitlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.js.topkotlinrepo.domain.repogitlist.RepoGit
import com.js.topkotlinrepo.domain.repogitlist.RepoGitUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepoGitListViewModel(private val useCase: RepoGitUseCase) : ViewModel() {

    val loading = MutableLiveData(true)
    val repoGitList = MutableLiveData<List<RepoGitItemViewModel>>()
    val repoGit = MutableLiveData<RepoGitItemViewModel>()

    fun loadGitRepositories() {
        loading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val repositories = useCase.getTopKotlinRepositories()
            repoGitList.postValue(mapViewModels(repositories))
            loading.postValue(false)
        }
    }

    private fun mapViewModels(list: List<RepoGit>) = list.map { repoGit ->
        RepoGitItemViewModel(repoGit.id, repoGit.name, repoGit.owner, repoGit.description, repoGit.stars, ::onSelectRepository)
    }

    private fun onSelectRepository(repo: RepoGitItemViewModel) {
        repoGit.postValue(repo)
    }
}
