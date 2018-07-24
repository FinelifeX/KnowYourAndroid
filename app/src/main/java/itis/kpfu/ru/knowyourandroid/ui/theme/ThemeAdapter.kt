package itis.kpfu.ru.knowyourandroid.ui.theme

import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import itis.kpfu.ru.knowyourandroid.R
import itis.kpfu.ru.knowyourandroid.model.ThemeGroup
import itis.kpfu.ru.knowyourandroid.ui.lesson.LessonFragment
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.item_theme_view.view.*

class ThemeAdapter(groups: List<ThemeGroup>, val activity: FragmentActivity?) :
        ExpandableRecyclerViewAdapter<ThemeViewHolder, LessonViewHolder>(groups) {

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_theme_view, parent, false)
        return ThemeViewHolder(view)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lesson_view, parent, false)
        return LessonViewHolder(view)
    }

    override fun onBindChildViewHolder(holder: LessonViewHolder, flatPosition: Int, group: ExpandableGroup<*>,
            childIndex: Int) {
        val lesson = (group as ThemeGroup).items[childIndex]
        holder.setLessonTitle(lesson)
        holder.itemView.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.container, LessonFragment.newInstance(lesson.name, group.title, group.items.size, childIndex))
                    .addToBackStack("LessonFragmentOf" + lesson.name)
                    .commit()
        }
    }

    override fun onBindGroupViewHolder(holder: ThemeViewHolder, flatPosition: Int, group: ExpandableGroup<*>) {
        holder.setThemeTitle(group)
    }
}
