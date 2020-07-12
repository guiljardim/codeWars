package com.example.codewars.ui.User

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
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.user_fragment.*
import javax.inject.Inject

class UserFragment : DaggerFragment(), SearchView.OnQueryTextListener{

    companion object {
        fun newInstance() = UserFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val listOfUser: MutableList<User>? = mutableListOf()

    private val userViewModel: UserViewModel by viewModels {
        viewModelFactory
    }

    private fun userObserver(userViewModel: UserViewModel){
        userViewModel.userLiveData.observe(
            viewLifecycleOwner,
            Observer {
                when (it.status) {
                    ViewData.Status.LOADING ->  {
                        progress_bar_user_fragment.visibility = View.VISIBLE
                    }

                    ViewData.Status.SUCCESS -> {
                        progress_bar_user_fragment.visibility = View.GONE
                        createUserList(it.data)
                    }

                    ViewData.Status.ERROR -> {
                        progress_bar_user_fragment.visibility = View.GONE
                        Toast.makeText(context,"User not found", Toast.LENGTH_LONG).show()
                    }
                }
            })
    }

    private fun createUserList(user: User?) {
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
        with(userViewModel){
            viewLifecycleOwner.lifecycle.addObserver(this)
            userObserver(this)
        }
        return inflater.inflate(R.layout.user_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun initListener() {
        search_view_user_fragment.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(name: String?): Boolean {
        name?.let { userViewModel.getUser(it)}
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }

}