package com.js.topkotlinrepo.ui.repogit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.js.topkotlinrepo.R
import com.js.topkotlinrepo.databinding.FragmentRepoGitBinding
import org.koin.android.viewmodel.ext.android.viewModel

class RepoGitFragment : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "RepoGitFragment"

        private const val ARG_REPO_NAME = "repoName"
        private const val ARG_REPO_OWNER = "repoOwner"

        fun newInstance(name: String, owner: String) = RepoGitFragment().apply {
            arguments = bundleOf(ARG_REPO_NAME to name, ARG_REPO_OWNER to owner)
        }
    }

    private val viewModel by viewModel<RepoGitViewModel>()
    private val name by lazy { requireArguments().getString(ARG_REPO_NAME, "") }
    private val owner by lazy { requireArguments().getString(ARG_REPO_OWNER, "") }

    private lateinit var binding: FragmentRepoGitBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repo_git, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.issueAdapter = RepoIssueAdapter(viewLifecycleOwner)
        binding.prAdapter = RepoPullRequestAdapter(viewLifecycleOwner)

        binding.insightsTitle.setOnClickListener { showDateDialog() }

        return binding.root
    }

    private fun showDateDialog() {
        val dates = viewModel.sortDates
        AlertDialog.Builder(requireActivity())
            .setTitle(R.string.repo_git_date_title)
            .setAdapter(DateArrayAdapter(requireActivity(), R.layout.item_repo_date, dates)) { _, position ->
                viewModel.loadIssuesAndPullRequests(name, owner, dates[position].startDate, dates[position].endDate)
            }
            .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.cancel()
            }
            .create()
            .show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            repoGit.observe(viewLifecycleOwner) { repo ->
                if (repo == null) {
                    dismiss()
                }
            }
        }

        viewModel.loadGitRepository(name, owner)
    }
}
