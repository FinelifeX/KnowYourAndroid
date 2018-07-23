package itis.kpfu.ru.knowyourandroid.model

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

import itis.kpfu.ru.knowyourandroid.model.Lesson

class ThemeGroup(title: String = "theme", items: List<Lesson> = listOf()) : ExpandableGroup<Lesson>(title, items)
