package itis.kpfu.ru.knowyourandroid.model

import io.realm.RealmObject

open class Answer(var content: String = "Answer", var correct: Boolean = false) : RealmObject()