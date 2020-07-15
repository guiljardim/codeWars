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
import kotlinx.android.synthetic.main.item_completed_challenge_list.view.*

class AuthoredChallengeAdapter(
    private val listOfAuthoredChallenges: List<AuthoredChallengeData>,
    private val context: Context?,
    private val listener: OnItemClickListener) :
    RecyclerView.Adapter<AuthoredChallengeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthoredChallengeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_authored_challenge_list, parent, false)
        return AuthoredChallengeViewHolder(view)
    }

    override fun getItemCount(): Int = listOfAuthoredChallenges.size

    override fun onBindViewHolder(holder: AuthoredChallengeViewHolder, position: Int) {
        listOfAuthoredChallenges.let {
            holder.bindView(it[position], listener)
        }
    }

    class AuthoredChallengeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(authoredChallenge: AuthoredChallengeData?, listener: OnItemClickListener) {
            itemView.text_view_name_authored_challenge.text = authoredChallenge?.name
            itemView.text_view_authored_date.text = authoredChallenge?.rankName

            itemView.setOnClickListener {
                listener.onItemClick(authoredChallenge?.id)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(idChallenge: String?)
    }


}
