package itis.kpfu.ru.knowyourandroid.repository.cache

import io.realm.Realm
import io.realm.RealmObject

class RewriteCache(val tClass: Class<out RealmObject>) {

    fun rewrite(list: List<RealmObject>){
        Realm.getDefaultInstance()
                .executeTransaction { realm ->
                    realm.delete(tClass)
                    realm.insert(list)
                }
    }

    fun rewrite(elem: RealmObject){
        Realm.getDefaultInstance()
                .executeTransaction { realm ->
                    realm.delete(tClass)
                    realm.insert(elem)
                }
    }
}