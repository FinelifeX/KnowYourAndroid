package itis.kpfu.ru.knowyourandroid.ui.theme

import android.view.View
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import itis.kpfu.ru.knowyourandroid.model.Lesson
import kotlinx.android.synthetic.main.item_lesson_view.view.*

class LessonViewHolder(itemView: View) : ChildViewHolder(itemView) {
    fun setLessonTitle(lesson: Lesson) {
        itemView.tv_header_lesson.text = lesson.name
    }
}