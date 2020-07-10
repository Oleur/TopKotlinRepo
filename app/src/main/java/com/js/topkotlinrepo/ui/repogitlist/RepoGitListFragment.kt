package com.js.topkotlinrepo.ui.repogitlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.js.topkotlinrepo.R
import com.js.topkotlinrepo.databinding.FragmentRepoGitListBinding
import com.js.topkotlinrepo.ui.repogit.RepoGitFragment
import org.koin.android.viewmodel.ext.android.viewModel

class RepoGitListFragment : Fragment() {

    private val viewModel by viewModel<RepoGitListViewModel>()

    private lateinit var binding: FragmentRepoGitListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repo_git_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.adapter = RepoGitListAdapter(viewLifecycleOwner)

        binding.list addDivider DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).apply {
            setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.shape_divider, requireContext().theme)!!)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.repoGit.observe(viewLifecycleOwner) { repo ->
            with(childFragmentManager) {
                RepoGitFragment.newInstance(repo.name, repo.owner).show(this, RepoGitFragment.TAG)
            }
        }

        viewModel.loadGitRepositories()
    }

    override fun onDestroyView() {
        binding.unbind()
        super.onDestroyView()
    }
}

private infix fun RecyclerView.addDivider(dividerItemDecoration: DividerItemDecoration) {
    addItemDecoration(dividerItemDecoration)
}
