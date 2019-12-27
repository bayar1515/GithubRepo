package com.githubrepo.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubrepo.R
import com.githubrepo.model.RepoModel
import kotlinx.android.synthetic.main.card_repo_list.view.*

class RepoListAdapter() : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    private var list :ArrayList<RepoModel> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_repo_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].getName()
    }

    fun setList(list:ArrayList<RepoModel>){
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.name
        val icom = itemView.icon
    }
}