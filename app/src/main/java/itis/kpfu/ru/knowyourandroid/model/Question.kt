package itis.kpfu.ru.knowyourandroid.model

import io.realm.RealmList
import io.realm.RealmObject
import java.util.Arrays

open class Question(var content: String = "question content",
        var answerList: RealmList<Answer> = RealmList()) : RealmObject()