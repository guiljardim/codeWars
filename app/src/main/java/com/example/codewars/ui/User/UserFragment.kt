package com.example.codewars.ui.User

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.user_fragment.*
import okhttp3.internal.notify
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

    private var listOfUser: List<User>? = null

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
                        progress_bar_user_fragment.visibility = View.VISIBLE
                        text_view_empty_state.visibility = View.GONE
                    }

                    ViewData.Status.SUCCESS -> {
                        progress_bar_user_fragment.visibility = View.GONE
                        text_view_empty_state.visibility = View.GONE
                        switch_compat_user_fragment.visibility = View.VISIBLE
                        text_view_latest_finding.visibility = View.VISIBLE
                        listOfUser = it.data
                        createUserList(it.data)
                    }

                    ViewData.Status.ERROR -> {
                        progress_bar_user_fragment.visibility = View.GONE
                        Toast.makeText(context,"User not found", Toast.LENGTH_LONG).show()
                    }
                }
            })
    }

    private fun createUserList(user: List<User>?) {
        recycle_view_user_fragment.layoutManager = LinearLayoutManager(context)
        recycle_view_user_fragment.adapter = UserAdapter(user?.toMutableList(), context, this)
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

    private fun orderUserByRank(list: List<User>?) {
        createUserList(list?.sortedBy { it.leaderboardPosition }?.toMutableList())
    }

    private fun orderUserBySearchTime(list: List<User>?) {
        createUserList(list)
    }

    override fun onQueryTextSubmit(name: String?): Boolean {
        name?.let { userViewModel.getUser(it)}
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }

    override fun onItemClick(user: String) {
        listener?.gotToChallengesActivity(user, this)
    }

}