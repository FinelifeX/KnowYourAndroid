package itis.kpfu.ru.knowyourandroid.model

import io.realm.RealmList
import io.realm.RealmObject

open class Test(var title: String = "testTitle", var questionList: RealmList<Question> = RealmList()) :
        RealmObject()