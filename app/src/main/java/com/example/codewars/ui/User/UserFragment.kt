package com.example.codewars.ui.User

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codewars.R
import com.example.codewars.data.model.User
import com.example.codewars.util.ViewData
import com.example.codewars.util.goneView
import com.example.codewars.util.visibilityView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.user_fragment.*
import javax.inject.Inject

class UserFragment : DaggerFragment(), SearchView.OnQueryTextListener, UserAdapter.OnItemClickListener{

    companion object {
        fun newInstance() = UserFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var listener: OnFragmentInteractionListener? = null

    private val userViewModel: UserViewModel by viewModels {
        viewModelFactory
    }

    private var listOfUser: MutableList<User>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        with(userViewModel){
            viewLifecycleOwner.lifecycle.addObserver(this)
            userObserver(this)
        }
        return inflater.inflate(R.layout.user_fragment, container, false)

    }

    private fun userObserver(userViewModel: UserViewModel){
        userViewModel.userLiveData.observe(
            viewLifecycleOwner,
            Observer {
                when (it.status) {
                    ViewData.Status.LOADING ->  {
                        progress_bar_user_fragment.visibilityView()
                        text_view_empty_state.goneView()
                    }

                    ViewData.Status.SUCCESS -> {
                        progress_bar_user_fragment.goneView()
                        text_view_empty_state.goneView()
                        switch_compat_user_fragment.visibilityView()
                        text_view_latest_finding.visibilityView()

                        listOfUser = it.data?.toMutableList()
                        listOfUser?.let { list -> createUserList(list) }
                    }

                    ViewData.Status.ERROR -> {
                        progress_bar_user_fragment.goneView()
                        Toast.makeText(context,"User not found", Toast.LENGTH_LONG).show()
                    }
                }
            })
    }

    private fun createUserList(user: MutableList<User>) {
        if(user.size > 5){
            user.remove(user.last())
        }

        recycle_view_user_fragment.layoutManager = LinearLayoutManager(context)
        recycle_view_user_fragment.adapter = UserAdapter(user, context, this)
    }

    private fun initListener() {
        search_view_user_fragment.setOnQueryTextListener(this)
        switch_compat_user_fragment.setOnCheckedChangeListener { compoundButton, b ->
            if(b){
                orderUserByRank(listOfUser)
            }else{
                orderUserBySearchTime(listOfUser)
            }
        }
    }

    private fun orderUserByRank(list: MutableList<User>?) {
        list?.sortedBy { it.leaderboardPosition }?.toMutableList()?.let { createUserList(it) }
    }

    private fun orderUserBySearchTime(list: MutableList<User>?) {
        list?.let { createUserList(it) }
    }

    override fun onQueryTextSubmit(name: String?): Boolean {
        name?.let { userViewModel.getUser(it)}
        clearSearchField()
        return true
    }

    private fun clearSearchField() {
        search_view_user_fragment.setQuery("", false)
        search_view_user_fragment.clearFocus()
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }

    override fun onItemClick(user: String) {
        listener?.gotToChallengesActivity(user, this)
    }
}

