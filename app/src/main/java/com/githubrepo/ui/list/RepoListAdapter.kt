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
    private var favoriteList:ArrayList<Int> = arrayListOf()
    private lateinit var listener :IRepoListAdapter
    constructor(listener :IRepoListAdapter):this(){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_repo_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].getName()

        if (favoriteList.contains(list[position].getId()))
            holder.icom.setImageResource(R.drawable.ic_favorite_selected)
        else
            holder.icom.setImageResource(R.drawable.ic_favorite_unselected)

        holder.itemView.setOnClickListener { listener.itemClicked(list[position]) }
    }

    fun setList(list:ArrayList<RepoModel>,favoriteList:ArrayList<Int>){
        this.list = list
        this.favoriteList = favoriteList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.name
        val icom = itemView.icon
    }

    interface IRepoListAdapter {
        fun itemClicked(repoModel: RepoModel)
    }
}