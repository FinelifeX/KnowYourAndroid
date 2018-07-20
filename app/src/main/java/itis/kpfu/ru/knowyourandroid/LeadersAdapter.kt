package itis.kpfu.ru.knowyourandroid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import itis.kpfu.ru.knowyourandroid.LeadersAdapter.LeadersViewHolder
import itis.kpfu.ru.knowyourandroid.R.layout.item_leadership_user
import itis.kpfu.ru.knowyourandroid.model.User
import kotlinx.android.synthetic.main.item_leadership_user.view.tv_level
import kotlinx.android.synthetic.main.item_leadership_user.view.tv_name
import kotlinx.android.synthetic.main.item_leadership_user.view.tv_position

/**
 * Created by Ilya Zakharchenko on 17.07.2018.
 */
class LeadersAdapter constructor(var leaders: List<User?>) : RecyclerView.Adapter<LeadersViewHolder>() {

    class LeadersViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeadersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(item_leadership_user, parent, false)
        return LeadersViewHolder(v)
    }

    override fun getItemCount(): Int = leaders.size

    override fun onBindViewHolder(holder: LeadersViewHolder, position: Int) {
        val pos = position + 1
        holder.itemView.tv_position.text = pos.toString()
        holder.itemView.tv_name.text = leaders[position]?.username
        holder.itemView.tv_level.text = leaders[position]?.exp.toString()
    }
}