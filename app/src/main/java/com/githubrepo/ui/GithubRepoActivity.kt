package com.githubrepo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.githubrepo.R
import com.githubrepo.ui.list.RepoListFragment
import kotlinx.android.synthetic.main.activity_github_repo.*

class GithubRepoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_repo)
        setSupportActionBar(toolbar_top)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar_top.setNavigationIcon(R.drawable.ic_back)
        toolbar_top.setNavigationOnClickListener { onBackPressed() }

        openFragment(RepoListFragment(),"list")
    }



    fun openFragment(fragment: Fragment, tag:String) {
        try {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment,tag)
            transaction.addToBackStack(null)
            transaction.commitAllowingStateLoss()
        }catch (e:IllegalStateException){

        }catch (e:IllegalStateException){

        }

    }

    fun setActionTitle(title:String){
        supportActionBar?.setTitle(title)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1)
            supportFragmentManager.popBackStack()
        else
            finish()
    }
}
