package com.example.codewars.ui.Challenges.completedChallenges

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codewars.R
import com.example.codewars.data.model.CompletedChallengeData
import kotlinx.android.synthetic.main.item_completed_challenge_list.view.*

class CompletedChallengeAdapter(private val listOfChallenges: List<CompletedChallengeData>, private val context: Context?) :
    RecyclerView.Adapter<CompletedChallengeAdapter.CompletedChallengeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedChallengeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_completed_challenge_list, parent, false)
        return CompletedChallengeViewHolder(view)
    }

    override fun getItemCount(): Int = listOfChallenges.size

    override fun onBindViewHolder(holder: CompletedChallengeViewHolder, position: Int) {
        listOfChallenges.let {
            holder.bindView(it[position])
        }
    }

    class CompletedChallengeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(completedChallengeData: CompletedChallengeData) {
            itemView.text_view_name_completed_challenge.text = completedChallengeData.name
        }
    }


}
