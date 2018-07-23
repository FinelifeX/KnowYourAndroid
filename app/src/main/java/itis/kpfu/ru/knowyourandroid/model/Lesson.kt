package itis.kpfu.ru.knowyourandroid.model

import io.realm.RealmList
import io.realm.RealmObject

open class Lesson(var name: String = "LessonName", var content: String = "LessonText",
        var imgReferences: RealmList<String> = RealmList()): RealmObject()

