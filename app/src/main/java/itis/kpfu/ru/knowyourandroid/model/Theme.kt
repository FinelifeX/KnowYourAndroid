package itis.kpfu.ru.knowyourandroid.model

import io.realm.RealmList
import io.realm.RealmObject

open class Theme(var title: String = "themeTitle", var lessons: RealmList<Lesson> = RealmList()) : RealmObject()