package itis.kpfu.ru.knowyourandroid.model

import com.google.firebase.database.IgnoreExtraProperties
import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by Ilya Zakharchenko on 21.07.2018.
 */
@IgnoreExtraProperties
open class HandbookClass(var name: String = "", var methodList: RealmList<HandbookMethod> = RealmList()) :
        RealmObject()