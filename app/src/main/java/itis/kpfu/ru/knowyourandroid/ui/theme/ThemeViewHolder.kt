package itis.kpfu.ru.knowyourandroid.ui.theme

import android.view.View
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import kotlinx.android.synthetic.main.item_theme_view.view.*

class ThemeViewHolder(itemView: View) : GroupViewHolder(itemView) {

    fun setThemeTitle(group: ExpandableGroup<*>) {
        itemView.tv_header_theme.text = group.title
    }
}