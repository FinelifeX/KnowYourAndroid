package itis.kpfu.ru.knowyourandroid.model

import com.google.firebase.database.IgnoreExtraProperties

/**
 * Created by Ilya Zakharchenko on 21.07.2018.
 */
@IgnoreExtraProperties
data class HandbookClass(var name: String = "", var methodList: MutableList<HandbookMethod> = ArrayList())