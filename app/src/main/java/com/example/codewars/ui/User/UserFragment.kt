package com.example.codewars.ui.User

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codewars.R
import com.example.codewars.data.model.User
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.user_fragment.*
import javax.inject.Inject

class UserFragment : DaggerFragment(), SearchView.OnQueryTextListener{

    companion object {
        fun newInstance() = UserFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val userViewModel: UserViewModel by viewModels {
        viewModelFactory
    }

    private val userObserver = Observer<User> {user ->
        createUserList(user)
    }

    private fun createUserList(user: User?) {
        val listOfUser: MutableList<User>? = mutableListOf()
        user?.let { listOfUser?.add(it) }
        recycle_view_user_fragment.layoutManager = LinearLayoutManager(context)
        recycle_view_user_fragment.adapter = UserAdapter(listOfUser, context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun initListener() {
        search_view_user_fragment.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(name: String?): Boolean {
        name?.let { userViewModel.getUser(it).observe(this, userObserver) }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }

}