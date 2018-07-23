package itis.kpfu.ru.knowyourandroid.ui.handbook.handbookClass

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import itis.kpfu.ru.knowyourandroid.R
import itis.kpfu.ru.knowyourandroid.model.HandbookClass
import itis.kpfu.ru.knowyourandroid.ui.handbook.handbookClass.HandbookClassAdapter.HandbookClassViewHolder
import kotlinx.android.synthetic.main.item_class.view.class_name

/**
 * Created by Ilya Zakharchenko on 21.07.2018.
 */
class HandbookClassAdapter(private val classes: List<HandbookClass>,
        private val fragment: HandbookClassListFragment) : RecyclerView.Adapter<HandbookClassViewHolder>() {

    class HandbookClassViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int = classes.size

    override fun onBindViewHolder(holder: HandbookClassViewHolder, position: Int) {
        holder.itemView.class_name.text = classes[position].name
        holder.itemView.setOnClickListener { fragment.onItemClick(position) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HandbookClassViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_class, parent, false)
        return HandbookClassViewHolder(view)
    }
}