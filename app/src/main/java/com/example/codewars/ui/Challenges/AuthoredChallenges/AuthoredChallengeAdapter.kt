package com.example.codewars.ui.Challenges.AuthoredChallenges

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codewars.R
import com.example.codewars.data.model.AuthoredChallengeData
import com.example.codewars.ui.Challenges.AuthoredChallenges.AuthoredChallengeAdapter.AuthoredChallengeViewHolder
import kotlinx.android.synthetic.main.item_authored_challenge_list.view.*

class AuthoredChallengeAdapter(
    private val listOfAuthoredChallenges: List<AuthoredChallengeData>,
    private val context: Context?) :
    RecyclerView.Adapter<AuthoredChallengeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthoredChallengeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_authored_challenge_list, parent, false)
        return AuthoredChallengeViewHolder(view)
    }

    override fun getItemCount(): Int = listOfAuthoredChallenges.size

    override fun onBindViewHolder(holder: AuthoredChallengeViewHolder, position: Int) {
        listOfAuthoredChallenges.let {
            holder.bindView(it[position])
        }
    }

    class AuthoredChallengeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(authoredChallenge: AuthoredChallengeData?) {
            itemView.text_view_name_authored_challenge.text = authoredChallenge?.name
        }
    }


}
