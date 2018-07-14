package itis.kpfu.ru.knowyourandroid

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

/**
 * Created by Ilya Zakharchenko on 12.07.2018.
 */
@IgnoreExtraProperties
data class User (@Exclude var uid : String? = null, var username : String? = null, var exp : Int = 0)