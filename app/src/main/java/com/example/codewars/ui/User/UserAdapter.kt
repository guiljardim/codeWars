package com.example.codewars.ui.User

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codewars.R
import com.example.codewars.data.model.Overall
import com.example.codewars.data.model.User
import com.example.codewars.util.getBetterLanguage
import kotlinx.android.synthetic.main.item_user_list.view.*

class UserAdapter(private val listOfUser: MutableList<User>?, private val context: Context?) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user_list, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = listOfUser?.size ?: 0

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        listOfUser?.let {
            holder.bindView(it[position])
        }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(user: User) {

            itemView.txtName.text = (user.name ?: user.username)
                .formatToExhibition("name")
            itemView.textLanguage.text = user.ranks.languages.getBetterLanguage()
                ?.formatToExhibition("Better Language")
            itemView.txtRank.text = user.boardPosition.toString()
                .formatToExhibition("Board Position")
            itemView.txtPositionLanguage.text =
                user.ranks.languages[user.ranks.languages.getBetterLanguage()]?.score.toString()
                    .formatToExhibition("Score")
        }
    }
}

private fun String.formatToExhibition(title: String): String? = "$title : $this"


