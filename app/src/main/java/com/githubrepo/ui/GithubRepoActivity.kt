package com.githubrepo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.githubrepo.R
import com.githubrepo.ui.list.RepoListFragment

class GithubRepoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_repo)

        openFragment(RepoListFragment(),"list")
    }

    private fun openFragment(fragment: Fragment, tag:String) {
        try {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment,tag)
            transaction.commitAllowingStateLoss()
        }catch (e:IllegalStateException){

        }catch (e:IllegalStateException){

        }

    }
}
