package itis.kpfu.ru.knowyourandroid.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import itis.kpfu.ru.knowyourandroid.model.HandbookClass
import itis.kpfu.ru.knowyourandroid.model.HandbookMethod
import itis.kpfu.ru.knowyourandroid.repository.cache.Cache
import itis.kpfu.ru.knowyourandroid.service.HandbookService

/**
 * Created by Ilya Zakharchenko on 21.07.2018.
 */
object HandbookRepository {

    private val classes = ArrayList<HandbookClass>()
    private val service = HandbookService

    fun loadClasses() {
        if (classes.isEmpty()) {
            FirebaseDatabase.getInstance().getReference("classes").addValueEventListener(object : ValueEventListener {

                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    for (dataSnapshot in p0.children) {
                        dataSnapshot.getValue(HandbookClass::class.java)?.let { it ->
                            for (methodSnapshot in dataSnapshot.child("methods").children) {
                                methodSnapshot.getValue(HandbookMethod::class.java)?.let(it.methodList::add)
                            }
                            classes.add(it)
                        }
                        service.notifyDataLoaded(classes)
                    }
                }
            })
        } else service.notifyDataLoaded(classes)
    }

    fun loadClasses(next: () -> Unit) {
        if (classes.isEmpty()) {
            FirebaseDatabase.getInstance().getReference("classes").addValueEventListener(object : ValueEventListener {

                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    for (dataSnapshot in p0.children) {
                        dataSnapshot.getValue(HandbookClass::class.java)?.let { it ->
                            for (methodSnapshot in dataSnapshot.child("methods").children) {
                                methodSnapshot.getValue(HandbookMethod::class.java)?.let(it.methodList::add)
                            }
                            classes.add(it)
                        }
                    }
                    Cache.addToCache(classes)
                    next()
                }
            })
        } else next()
    }

    fun getClass(position: Int) = classes[position]
}