package com.githubrepo.ui.list

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubrepo.R
import com.githubrepo.helpers.DBHelper
import com.githubrepo.helpers.SharedController
import com.githubrepo.model.RepoModel
import com.githubrepo.network.ApiClient
import com.githubrepo.ui.GithubRepoActivity
import com.githubrepo.ui.detail.RepoDetailFragment
import com.wang.avi.AVLoadingIndicatorView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RepoListFragment : Fragment(),RepoListAdapter.IRepoListAdapter {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var  adapter: RepoListAdapter
    private  val db by lazy { DBHelper(activity as Activity)  }
    private var favoriteList:ArrayList<Int> = arrayListOf()
    private lateinit var progress:AVLoadingIndicatorView
    private lateinit var searchView: SearchView
    private lateinit var sharedController: SharedController
    private lateinit var noDataText :TextView
    private var query = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_repo_list, container, false)

        sharedController = SharedController(activity as Activity)
        progress = v.findViewById(R.id.progress)
        noDataText = v.findViewById(R.id.noDataText)
        progress.smoothToHide()
        searchView = v.findViewById(R.id.searchView)
        recyclerView = v.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity as Activity)
        adapter = RepoListAdapter(this)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null)
                    query = p0
                getRepoList(query)
                return true
            }
        })

        (activity as GithubRepoActivity).setActionTitle(resources.getString(R.string.home))

        return v
    }

    override fun onResume() {
        super.onResume()
        favoriteList = db.readData()
        getRepoList(query)
    }

    private fun getRepoList(userName:String){
        if (!userName.equals("")) {
            progress.smoothToShow()
            ApiClient().create().getProjectList(user = userName)
                .enqueue(object : Callback<ArrayList<RepoModel>> {
                    override fun onFailure(call: Call<ArrayList<RepoModel>>, t: Throwable) {
                        sharedController.showErrorToast(resources.getString(R.string.repo_list_error))
                        progress.smoothToHide()

                    }

                    override fun onResponse(
                        call: Call<ArrayList<RepoModel>>,
                        response: Response<ArrayList<RepoModel>>
                    ) {
                        if (response.raw().code() == 404) {
                            sharedController.showErrorToast(resources.getString(R.string.repo_list_error))
                            progress.smoothToHide()
                        } else
                            response.body()?.let { getRepoListSuccess(it) }
                    }
                })
        }
    }

    private fun getRepoListSuccess(repoList:ArrayList<RepoModel>){
        if (repoList.size == 0 ){
            noDataText.visibility = View.VISIBLE
        }else {
            noDataText.visibility = View.GONE
            adapter.setList(repoList,favoriteList)
            progress.smoothToHide()
        }

    }

    override fun itemClicked(repoModel: RepoModel) {
        (activity as GithubRepoActivity).openFragment(RepoDetailFragment.newInstance(repoModel),"detail")
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RepoListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
