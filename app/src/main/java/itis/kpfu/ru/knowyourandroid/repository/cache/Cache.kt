package itis.kpfu.ru.knowyourandroid.repository.cache

import io.realm.Realm
import io.realm.RealmObject

object Cache {

    fun clear() {
        Realm.getDefaultInstance()
                .executeTransaction { realm ->
                    realm.deleteAll()
                }
    }

    fun addToCache(list: List<RealmObject>) {
        Realm.getDefaultInstance()
                .executeTransaction { realm ->
                    realm.insert(list)
                }
    }

    fun addToCache(elem: RealmObject) {
        Realm.getDefaultInstance()
                .executeTransaction { realm ->
                    realm.insert(elem)
                }
    }
}