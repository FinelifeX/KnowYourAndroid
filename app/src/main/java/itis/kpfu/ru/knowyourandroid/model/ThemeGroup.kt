package itis.kpfu.ru.knowyourandroid.model

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class ThemeGroup(title: String = "theme", items: List<Lesson> = listOf()) : ExpandableGroup<Lesson>(title, items)
