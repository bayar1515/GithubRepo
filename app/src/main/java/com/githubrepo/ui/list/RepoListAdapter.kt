package com.githubrepo.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubrepo.R

class RepoListAdapter() : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_repo_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    fun setList(){

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}