package com.js.topkotlinrepo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.observe
import com.js.topkotlinrepo.R
import com.js.topkotlinrepo.ui.login.AuthFragment
import com.js.topkotlinrepo.ui.login.*
import com.js.topkotlinrepo.ui.repogitlist.RepoGitListFragment
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.state.observe(this) { state ->
            supportFragmentManager.commit {
                when(state) {
                    is NavigateToMain -> replace(R.id.container, RepoGitListFragment())
                    is NavigateToLogin -> replace(R.id.container, LoginFragment())
                    is NavigateToAuth -> replace(R.id.container,
                        AuthFragment()
                    )
                }
            }
        }

        viewModel.isAuthenticate()
    }
}
