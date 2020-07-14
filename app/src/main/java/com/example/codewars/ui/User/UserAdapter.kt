package com.example.codewars.ui.User

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codewars.R
import com.example.codewars.data.model.User
import com.example.codewars.util.formatToExhibition
import com.example.codewars.util.getBetterLanguage
import kotlinx.android.synthetic.main.item_user_list.view.*

class UserAdapter(private val listOfUser: MutableList<User>?, private val context: Context?, private val listener: OnItemClickListener)
    : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user_list, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = listOfUser?.size ?: 0

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        listOfUser?.let {
            holder.bindView(it[position], listener)
        }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(user: User, listener: OnItemClickListener) {

            itemView.txtName.text = (user.name ?: user.username).toUpperCase()
            itemView.textLanguage.text = user.ranks.languages.getBetterLanguage()?.toUpperCase()
            itemView.txtRank.text = user.leaderboardPosition.toString().formatToExhibition("RANK")
            itemView.txtPositionLanguage.text = user.ranks.languages[user.ranks.languages.getBetterLanguage()]?.score.toString()

            itemView.setOnClickListener{
                listener.onItemClick(user.username)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(user: String)
    }

}



