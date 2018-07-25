package itis.kpfu.ru.knowyourandroid.model

import io.realm.RealmObject

/**
 * Created by Ilya Zakharchenko on 21.07.2018.
 */
open class HandbookMethod(var name: String = "", var description: String = "") : RealmObject()