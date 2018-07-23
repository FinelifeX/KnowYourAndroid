package itis.kpfu.ru.knowyourandroid.ui.handbook.handbookMethod

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import itis.kpfu.ru.knowyourandroid.R
import itis.kpfu.ru.knowyourandroid.R.drawable.ic_arrow_down
import itis.kpfu.ru.knowyourandroid.R.drawable.ic_arrow_up
import itis.kpfu.ru.knowyourandroid.model.HandbookMethod
import itis.kpfu.ru.knowyourandroid.ui.handbook.handbookMethod.HandbookMethodAdapter.HandbookMethodViewHolder
import kotlinx.android.synthetic.main.item_method.view.expandable_container
import kotlinx.android.synthetic.main.item_method.view.image_arrow
import kotlinx.android.synthetic.main.item_method.view.method_descr
import kotlinx.android.synthetic.main.item_method.view.method_name

/**
 * Created by Ilya Zakharchenko on 21.07.2018.
 */
class HandbookMethodAdapter(private val methods: List<HandbookMethod>) : RecyclerView
.Adapter<HandbookMethodViewHolder>() {

    class HandbookMethodViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    private var expandedPosition = -1
    private var previousExpandedPosition = -1

    override fun getItemCount(): Int = methods.size

    override fun onBindViewHolder(holder: HandbookMethodViewHolder, position: Int) {
        val isExpanded = holder.adapterPosition == expandedPosition
        val imageResourceId: Int
        if (isExpanded) {
            previousExpandedPosition = holder.adapterPosition
            holder.itemView.expandable_container.visibility = View.VISIBLE
            imageResourceId = ic_arrow_up
        } else {
            holder.itemView.expandable_container.visibility = View.GONE
            imageResourceId = ic_arrow_down
        }
        Glide.with(holder.itemView)
                .load(imageResourceId)
                .into(holder.itemView.image_arrow)
        holder.itemView.method_name.text = methods[position].name
        holder.itemView.method_descr.text = methods[position].description
        holder.itemView.setOnClickListener {
            expandedPosition = if (isExpanded) -1 else holder.adapterPosition
            notifyItemChanged(previousExpandedPosition)
            notifyItemChanged(holder.adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HandbookMethodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_method, parent, false)
        return HandbookMethodViewHolder(view)
    }
}