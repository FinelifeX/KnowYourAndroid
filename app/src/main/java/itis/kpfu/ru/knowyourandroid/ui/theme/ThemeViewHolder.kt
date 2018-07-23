package itis.kpfu.ru.knowyourandroid.ui.theme

import android.view.View
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import itis.kpfu.ru.knowyourandroid.R
import kotlinx.android.synthetic.main.item_theme_view.view.*

class ThemeViewHolder(itemView: View) : GroupViewHolder(itemView) {
    fun setThemeTitle(group: ExpandableGroup<*>) {
        itemView.tv_header_theme.text = group.title
    }

    override fun expand() {
        itemView.iv_header_theme.setImageResource(R.drawable.ic_arrow_up)
        itemView.tv_header_theme.maxLines = 5
    }

    override fun collapse() {
        itemView.iv_header_theme.setImageResource(R.drawable.ic_arrow_down)
        itemView.tv_header_theme.maxLines = 1
    }
}