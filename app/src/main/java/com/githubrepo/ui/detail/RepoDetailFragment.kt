package com.githubrepo.ui.detail


import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import com.githubrepo.R
import com.githubrepo.helpers.DBHelper
import com.githubrepo.helpers.SharedController
import com.githubrepo.model.RepoModel
import com.githubrepo.ui.GithubRepoActivity
import com.squareup.picasso.Picasso

class RepoDetailFragment : Fragment() {

    private lateinit var image :ImageView
    private lateinit var name :TextView
    private lateinit var stars :TextView
    private lateinit var openIssues :TextView
    private lateinit var repo: RepoModel
    private var repoId = 0
    private lateinit var sharedController: SharedController
    private  val db by lazy { DBHelper(activity as Activity)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            repo = it.getSerializable("repo") as RepoModel
            setHasOptionsMenu(true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_repo_detail, container, false)

        repo.getName()?.let { (activity as GithubRepoActivity).setActionTitle(it) }
        repoId = repo.getId()!!
        image = v.findViewById(R.id.image)
        name = v.findViewById(R.id.name)
        stars = v.findViewById(R.id.stars)
        openIssues = v.findViewById(R.id.openIssues)

        Picasso.get().load(repo.getOwner()?.getAvatarUrl()).into(image)
        name.text = repo.getOwner()?.getLogin()
        stars.text = String.format(resources.getString(R.string.stars_holder),repo.getStargazersCount())
        openIssues.text = String.format(resources.getString(R.string.open_issues_holder),repo.getOpenIssuesCount())
        sharedController = SharedController(activity as Activity)

        return v
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite,menu)

        val item = menu.getItem(0)
        if (sharedController.isHasFromFavorites(repoId,db))
            item.setIcon(R.drawable.ic_favorite_selected)
        else
            item.setIcon(R.drawable.ic_favorite_unselected)
        super.onCreateOptionsMenu(menu, inflater)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_favorite -> {
                if (!sharedController.isHasFromFavorites(repoId,db)) {
                    db.insertData(repoId)
                    item.setIcon(R.drawable.ic_favorite_selected)
                    sharedController.showSuccessToast(resources.getString(R.string.add_favorites_success))
                }else {
                    db.delete(repoId)
                    item.setIcon(R.drawable.ic_favorite_unselected)
                    sharedController.showSuccessToast(resources.getString(R.string.remove_favorites_success))
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        @JvmStatic
        fun newInstance(repo:RepoModel) =
            RepoDetailFragment().apply {
                arguments = Bundle().apply {
                   putSerializable("repo",repo)
                }
            }
    }

}
