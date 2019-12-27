package com.githubrepo.ui.list

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubrepo.R
import com.githubrepo.model.RepoModel
import com.githubrepo.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RepoListFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var  adapter: RepoListAdapter

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

        recyclerView = v.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity as Activity)
        adapter = RepoListAdapter()
        recyclerView.adapter = adapter

        getRepoList()

        return v
    }


    private fun getRepoList(){
        ApiClient().create().getProjectList("bayar1515").enqueue(object :Callback<ArrayList<RepoModel>>{
            override fun onFailure(call: Call<ArrayList<RepoModel>>, t: Throwable) {

            }

            override fun onResponse(call: Call<ArrayList<RepoModel>>, response: Response<ArrayList<RepoModel>>) {
                response.body()?.let { getRepoListSuccess(it) }
            }
        })
    }

    private fun getRepoListSuccess(repoList:ArrayList<RepoModel>){
        adapter.setList(repoList)
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
